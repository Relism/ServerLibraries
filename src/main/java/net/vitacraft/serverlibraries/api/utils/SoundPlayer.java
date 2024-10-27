package net.vitacraft.serverlibraries.api.utils;

import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.vitacraft.serverlibraries.ServerLibraries;

public class SoundPlayer {

    /*
        * CURRENT LOCATION SECTION
    */

    /**
     * Plays a sound for a player at their current location
     * @param player The player to play the sound for
     * @param soundEvent The sound event to play
     * @param volume The volume of the sound
     * @param pitch The pitch of the sound
     */
    public static void playSoundForPlayer(ServerPlayerEntity player, SoundEvent soundEvent, float volume, float pitch) {
        RegistryEntry<SoundEvent> soundEntry = Registries.SOUND_EVENT.getEntry(soundEvent);
        Vec3d playerPos = player.getPos();
        PlaySoundS2CPacket soundPacket = new PlaySoundS2CPacket(
                soundEntry,
                SoundCategory.PLAYERS,
                playerPos.x, playerPos.y, playerPos.z,
                volume,
                pitch,
                0
        );
        player.networkHandler.sendPacket(soundPacket);
    }

    /**
     * Plays a sound for a player at their current location
     * @param player The player to play the sound for
     * @param soundEvent The sound event to play
     * @param soundCategory The sound category to play the sound in
     * @param volume The volume of the sound
     * @param pitch The pitch of the sound
     */
    public static void playSoundForPlayer(ServerPlayerEntity player, SoundEvent soundEvent, SoundCategory soundCategory, float volume, float pitch) {
        RegistryEntry<SoundEvent> soundEntry = Registries.SOUND_EVENT.getEntry(soundEvent);
        Vec3d playerPos = player.getPos();
        PlaySoundS2CPacket soundPacket = new PlaySoundS2CPacket(
                soundEntry,
                soundCategory,
                playerPos.x, playerPos.y, playerPos.z,
                volume,
                pitch,
                0
        );
        player.networkHandler.sendPacket(soundPacket);
    }


    /*
        * SPECIFIC POSITION SECTION
    */

    /**
     * Plays a sound for a player at a specific position
     * @param player The player to play the sound for
     * @param soundEvent The sound event to play
     * @param volume The volume of the sound
     * @param pitch The pitch of the sound
     * @param pos The position to play the sound at
     */
    public static void playSoundForPlayer(ServerPlayerEntity player, SoundEvent soundEvent, float volume, float pitch, Vec3d pos) {
        RegistryEntry<SoundEvent> soundEntry = Registries.SOUND_EVENT.getEntry(soundEvent);
        PlaySoundS2CPacket soundPacket = new PlaySoundS2CPacket(
                soundEntry,
                SoundCategory.PLAYERS,
                pos.x, pos.y, pos.z,
                volume,
                pitch,
                0
        );
        player.networkHandler.sendPacket(soundPacket);
    }

    /**
     * Plays a sound for a player at a specific position
     * @param player The player to play the sound for
     * @param soundEvent The sound event to play
     * @param soundCategory The sound category to play the sound in
     * @param volume The volume of the sound
     * @param pitch The pitch of the sound
     * @param pos The position to play the sound at
     */
    public static void playSoundForPlayer(ServerPlayerEntity player, SoundEvent soundEvent, SoundCategory soundCategory, float volume, float pitch, Vec3d pos) {
        RegistryEntry<SoundEvent> soundEntry = Registries.SOUND_EVENT.getEntry(soundEvent);
        PlaySoundS2CPacket soundPacket = new PlaySoundS2CPacket(
                soundEntry,
                soundCategory,
                pos.x, pos.y, pos.z,
                volume,
                pitch,
                0
        );
        player.networkHandler.sendPacket(soundPacket);
    }

    /*
        * SPECIFIC LOCATION SECTION
     */

    /**
     * Plays a sound for a player at a specific location
     * @param player The player to play the sound for
     * @param soundEvent The sound event to play
     * @param volume The volume of the sound
     * @param pitch The pitch of the sound
     * @param x The x coordinate of the position to play the sound at
     * @param y The y coordinate of the position to play the sound at
     * @param z The z coordinate of the position to play the sound at
     */
    public static void playSoundForPlayer(ServerPlayerEntity player, SoundEvent soundEvent, float volume, float pitch, double x, double y, double z) {
        RegistryEntry<SoundEvent> soundEntry = Registries.SOUND_EVENT.getEntry(soundEvent);
        PlaySoundS2CPacket soundPacket = new PlaySoundS2CPacket(
                soundEntry,
                SoundCategory.PLAYERS,
                x, y, z,
                volume,
                pitch,
                0
        );
        player.networkHandler.sendPacket(soundPacket);
    }

    /**
     * Plays a sound for a player at a specific location
     * @param player The player to play the sound for
     * @param soundEvent The sound event to play
     * @param volume The volume of the sound
     * @param pitch The pitch of the sound
     * @param x The x coordinate of the position to play the sound at
     * @param y The y coordinate of the position to play the sound at
     * @param z The z coordinate of the position to play the sound at
     * @param category The sound category to play the sound in
     */
    public static void playSoundForPlayer(ServerPlayerEntity player, SoundEvent soundEvent, float volume, float pitch, double x, double y, double z, SoundCategory category) {
        RegistryEntry<SoundEvent> soundEntry = Registries.SOUND_EVENT.getEntry(soundEvent);
        PlaySoundS2CPacket soundPacket = new PlaySoundS2CPacket(
                soundEntry,
                category,
                x, y, z,
                volume,
                pitch,
                0
        );
        player.networkHandler.sendPacket(soundPacket);
    }

    /*
        * BROADCASTING SECTION
    */

    /**
     * Broadcasts a sound to all players at their current location
     * @param soundEvent The sound event to play
     * @param volume The volume of the sound
     * @param pitch The pitch of the sound
     */
    public static void broadcastSound(SoundEvent soundEvent, float volume, float pitch) {
        for (ServerPlayerEntity player : ServerLibraries.getServer().getPlayerManager().getPlayerList()) {
            playSoundForPlayer(player, soundEvent, volume, pitch);
        }
    }

    /**
     * Broadcasts a sound to all players at their current location
     * @param soundEvent The sound event to play
     * @param soundCategory The sound category to play the sound in
     * @param volume The volume of the sound
     * @param pitch The pitch of the sound
     */
    public static void broadcastSound(SoundEvent soundEvent, SoundCategory soundCategory, float volume, float pitch) {
        for (ServerPlayerEntity player : ServerLibraries.getServer().getPlayerManager().getPlayerList()) {
            playSoundForPlayer(player, soundEvent, soundCategory, volume, pitch);
        }
    }

    /**
     * Broadcasts a sound to all players at a specific position
     * @param soundEvent The sound event to play
     * @param volume The volume of the sound
     * @param pitch The pitch of the sound
     * @param pos The position to play the sound at
     */
    public static void broadcastSound(SoundEvent soundEvent, float volume, float pitch, Vec3d pos) {
        for (ServerPlayerEntity player : ServerLibraries.getServer().getPlayerManager().getPlayerList()) {
            playSoundForPlayer(player, soundEvent, volume, pitch, pos);
        }
    }

    /**
     * Broadcasts a sound to all players at a specific position
     * @param soundEvent The sound event to play
     * @param soundCategory The sound category to play the sound in
     * @param volume The volume of the sound
     * @param pitch The pitch of the sound
     * @param pos The position to play the sound at
     */
    public static void broadcastSound(SoundEvent soundEvent, SoundCategory soundCategory, float volume, float pitch, Vec3d pos) {
        for (ServerPlayerEntity player : ServerLibraries.getServer().getPlayerManager().getPlayerList()) {
            playSoundForPlayer(player, soundEvent, soundCategory, volume, pitch, pos);
        }
    }

    /**
     * Broadcasts a sound to all players at a specific location
     * @param soundEvent The sound event to play
     * @param volume The volume of the sound
     * @param pitch The pitch of the sound
     * @param x The x coordinate of the position to play the sound at
     * @param y The y coordinate of the position to play the sound at
     * @param z The z coordinate of the position to play the sound at
     */
    public static void broadcastSound(SoundEvent soundEvent, float volume, float pitch, double x, double y, double z) {
        for (ServerPlayerEntity player : ServerLibraries.getServer().getPlayerManager().getPlayerList()) {
            playSoundForPlayer(player, soundEvent, volume, pitch, x, y, z);
        }
    }

    /**
     * Broadcasts a sound to all players at a specific location
     * @param soundEvent The sound event to play
     * @param volume The volume of the sound
     * @param pitch The pitch of the sound
     * @param x The x coordinate of the position to play the sound at
     * @param y The y coordinate of the position to play the sound at
     * @param z The z coordinate of the position to play the sound at
     * @param category The sound category to play the sound in
     */
    public static void broadcastSound(SoundEvent soundEvent, float volume, float pitch, double x, double y, double z, SoundCategory category) {
        for (ServerPlayerEntity player : ServerLibraries.getServer().getPlayerManager().getPlayerList()) {
            playSoundForPlayer(player, soundEvent, volume, pitch, x, y, z, category);
        }
    }
}
