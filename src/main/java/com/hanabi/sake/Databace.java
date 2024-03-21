package com.hanabi.sake;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.*;

public class Databace {
    private final String DB_name = "database.db";
    private Connection connection = null;
    private Statement statement = null;

    public Databace() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + this.DB_name);
            this.statement = connection.createStatement();

            statement.close();
        } catch (Exception e) {
            Bukkit.getLogger().warning(e.toString());
        }
    }

    public void CloseConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            Bukkit.getLogger().warning(e.toString());
        }
    }

    public void Ybwisky(Player player) {
        String player_uuid = player.getUniqueId().toString();

        try {
            this.statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select money from moneydata where uuid = '" + player_uuid + "'");
            int player_money = resultSet.getInt("money");

            if(player_money < 10000){
                player.sendMessage(ChatColor.RED  + "" + ChatColor.BOLD + "所持金が不足しています");
                return;
            }

            player_money = player_money - 10000;

            resultSet.close();

            this.statement.executeUpdate("update moneydata set money = " + player_money + " where uuid = '" + player_uuid + "'");
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "購入しました！");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
