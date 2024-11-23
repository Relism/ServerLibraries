package net.vitacraft.serverlibraries.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.vitacraft.serverlibraries.api.utils.SoundPlayer;
import net.vitacraft.serverlibraries.api.utils.msg;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Stream;

public class RandomSoundCommand {
    public static void register() {
        CommandRegistrationCallback.EVENT.register(RandomSoundCommand::register);
    }

    private static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, CommandManager.RegistrationEnvironment environment) {
        dispatcher.register(CommandManager.literal("randomsound")
                .then(CommandManager.argument("mode", StringArgumentType.word())
                        .then(CommandManager.argument("volume", FloatArgumentType.floatArg(0.0f, 2.0f))
                                .then(CommandManager.argument("pitch", FloatArgumentType.floatArg(0.0f, 2.0f))
                                        .executes(context -> {
                                            ServerCommandSource source = context.getSource();
                                            String mode = StringArgumentType.getString(context, "mode");
                                            float volume = FloatArgumentType.getFloat(context, "volume");
                                            float pitch = FloatArgumentType.getFloat(context, "pitch");

                                            SoundEvent sound = playSound(source, mode, volume, pitch);
                                            msg.send(Objects.requireNonNull(source.getPlayer()), "Playing sound: " + sound.getId().toString());
                                            return 1;
                                        }))))
                .executes(context -> {
                    ServerCommandSource source = context.getSource();
                    String mode = StringArgumentType.getString(context, "mode");

                    SoundEvent sound = playSound(source, mode, 1.0f, 1.0f);
                    msg.send(Objects.requireNonNull(source.getPlayer()), "Playing sound: " + sound.getId().toString());
                    return 1;
                }));
    }

    private static SoundEvent playSound(ServerCommandSource source, String mode, float volume, float pitch) {
        SoundEvent randomSound = getRandomSoundEvent();

        if ("self".equalsIgnoreCase(mode) && source.getEntity() instanceof ServerPlayerEntity player) {
            SoundPlayer.playSoundForPlayer(player, randomSound, volume, pitch);
        } else if ("broadcast".equalsIgnoreCase(mode)) {
            SoundPlayer.broadcastSound(randomSound, volume, pitch);
        } else {
            msg.send(Objects.requireNonNull(source.getPlayer()), "Invalid mode specified. Use 'self' or 'broadcast'.");
        }

        return randomSound;
    }

    private static SoundEvent getRandomSoundEvent() {
        List<SoundEvent> soundEvents = Stream.of(SoundEvents.class.getDeclaredFields())
                .map(field -> {
                    try {
                        Object fieldValue = field.get(null);
                        if (fieldValue instanceof SoundEvent) {
                            return (SoundEvent) fieldValue;
                        } else if (fieldValue instanceof RegistryEntry.Reference<?> ref) {
                            if (ref.value() instanceof SoundEvent) {
                                return (SoundEvent) ref.value();
                            }
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .toList();

        Random random = new Random();
        return soundEvents.get(random.nextInt(soundEvents.size()));
    }
}
