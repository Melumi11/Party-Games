package com.lielamar.partygames.utils;

import com.lielamar.lielsutils.MathUtils;
import com.lielamar.partygames.Main;
import com.lielamar.partygames.game.Minigame;
import com.lielamar.partygames.modules.CustomPlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Map;

public class GameUtils {

    /**
     * Get a sorted list of the players whereas i=0 is the player with most score and so on
     *
     * @param players               An array of all players to sort
     * @param type                  The sort type (By Minigame Score or By Overall Score)
     * @param randomizeEqualScore   Whether or not to randomize the sorted list if 2 objects has the same score
     * @return                      Sorted List of {@link CustomPlayer} objects
     */
    public static CustomPlayer[] sortCustomPlayersList(CustomPlayer[] players, SortType type, boolean randomizeEqualScore) {
        CustomPlayer[] playersCopy = new CustomPlayer[players.length];

        int index = 0;
        for(CustomPlayer cp : players) {
            if(cp != null) {
                playersCopy[index] = cp;
                index++;
            }
        }

        for(int i = 0; i < playersCopy.length - 1 ; i++) {
            if(playersCopy[i] == null) break;
            for(int j = 0; j < playersCopy.length - i - 1; j++) {
                if(playersCopy[j] == null) continue;
                if(playersCopy[j+1] == null) continue;

                if(type == SortType.BY_MINIGAME_SCORE) {
                    if(playersCopy[j].getMinigameScore() < playersCopy[j + 1].getMinigameScore()) {
                        CustomPlayer tmp = playersCopy[j];
                        playersCopy[j] = playersCopy[j+1];
                        playersCopy[j+1] = tmp;
                    }
                    if(playersCopy[j].getMinigameScore() == playersCopy[j + 1].getMinigameScore() && (!randomizeEqualScore || Main.rnd.nextInt(2) == 0)) {
                        CustomPlayer tmp = playersCopy[j];
                        playersCopy[j] = playersCopy[j+1];
                        playersCopy[j+1] = tmp;
                    }
                } else if(type == SortType.BY_SCORE) {
                    if(playersCopy[j].getScore() < playersCopy[j+1].getScore()) {
                        CustomPlayer tmp = playersCopy[j];
                        playersCopy[j] = playersCopy[j+1];
                        playersCopy[j+1] = tmp;
                    }
                    if(playersCopy[j].getScore() == playersCopy[j + 1].getScore() && (!randomizeEqualScore || Main.rnd.nextInt(2) == 0)) {
                        CustomPlayer tmp = playersCopy[j];
                        playersCopy[j] = playersCopy[j+1];
                        playersCopy[j+1] = tmp;
                    }
                }
            }
        }

        return playersCopy;
    }

    /**
     * Prints a minigame preparation
     *
     * @param minigame   Minigame to print the preparationof
     */
    public static void printMinigamePreparation(Minigame minigame) {
        String desc = ChatColor.YELLOW + "" + ChatColor.BOLD + "    " + minigame.getGameType().getDescription();
        StringBuilder name = new StringBuilder();
        for(int i = 0; i < (desc.length()/2 + desc.length()/4 - minigame.getGameType().getName().length()); i++) {
            name.append(" ");
        }

        minigame.getGame().infoPlayers(ChatColor.GREEN + "" + ChatColor.BOLD + "------------------------------------------");
        minigame.getGame().infoPlayers(ChatColor.WHITE + "" + ChatColor.BOLD + name.toString() + minigame.getGameType().getName());
        minigame.getGame().infoPlayers(desc);
        minigame.getGame().infoPlayers(ChatColor.GREEN + "" + ChatColor.BOLD + "------------------------------------------");
    }

    /**
     * Assigns players with different colors
     *
     * @param players          Array of {@link CustomPlayer}s to assign
     * @param wool_colors      Array of available colors
     * @param assignedColors   Map of colors assigned to players
     */
    public static void assignColorsToPlayers(CustomPlayer[] players, Integer[] wool_colors, Map<Integer, CustomPlayer> assignedColors) {
        for(int i = 0; i < players.length; i++) {
            if(players[i] == null) continue;
            assignedColors.put(wool_colors[i], players[i]);
        }
    }

    /**
     * Returns a {@link CustomPlayer}'s assigned color in a map
     *
     * @param cp               CustomPlayer to get the assigned color of
     * @param assignedColors   Map of assigned colors
     * @return                 The assigned color of the player
     */
    public static int getPlayerAssignedColor(CustomPlayer cp, Map<Integer, CustomPlayer> assignedColors) {
        for(int i : assignedColors.keySet()) {
            if(!assignedColors.containsKey(i)) continue;
            if(assignedColors.get(i) == cp) return i;
        }
        return -1;
    }

    /**
     * Calculates the distance of a player from a location based on start and finish
     *
     * @param player    Player to calculate distance of
     * @param startX    Start Line X coordinate
     * @param finishX   Finish Line X coordinate
     * @param startZ    Start Line Z coordinate
     * @param finishZ   Finish Line Z coordinate
     * @return          Distance of the given player from the finish line based on start line
     */
    public static int getDistanceFromLocation(Player player, double startX, double finishX, double startZ, double finishZ) {
        int distanceFromStartToFinish = (int) MathUtils.XZDistance(startX, finishX, startZ, finishZ);
        int distanceFromPlayerToFinish = (int) MathUtils.XZDistance(player.getLocation().getX(), finishX, player.getLocation().getZ(), finishZ);

        return distanceFromStartToFinish - distanceFromPlayerToFinish;
    }

    public enum SortType {
        BY_SCORE,
        BY_MINIGAME_SCORE
    }
}
