package com.hanabi.sake;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public final class Sake extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof Villager) {
            // イベントが村人を右クリックした場合

            // プレイヤーを取得
            Player player = event.getPlayer();

            // カスタムインベントリを開く
           createCustomInventory(player);
        }
    }
    private Inventory createCustomInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(new CustomInventoryHolder(), 9 , "バーテンダー");

        //Ybウィスキーの追加
        ItemStack a = new ItemStack(Material.POTION);
        PotionMeta ameta = (PotionMeta) a.getItemMeta();
        ameta.setDisplayName("Ybウィスキー" );
        ameta.setLore(Arrays.asList("購入金額　10000円", "当選金額　100000円")); // ここで複数行の説明を設定
        ameta.setColor(Color.GRAY);
        a.setItemMeta(ameta);

        ItemStack b = new ItemStack(Material.POTION);
        PotionMeta bmeta = (PotionMeta) b.getItemMeta();
        bmeta.setDisplayName("大石ウォッカ" );
        bmeta.setLore(Arrays.asList("購入金額　1000円", "当選金額　200000円")); // ここで複数行の説明を設定
        bmeta.setColor(Color.YELLOW);
        b.setItemMeta(bmeta);

        ItemStack c = new ItemStack(Material.POTION);
        PotionMeta cmeta = (PotionMeta) c.getItemMeta();
        cmeta.setDisplayName("無限岩崎スカッシュ" );
        cmeta.setLore(Arrays.asList("購入金額　100000円", "当選金額　90000円", "一度当たるともう一回続く...？")); // ここで複数行の説明を設定
        cmeta.setColor(Color.BLUE);
        c.setItemMeta(cmeta);

        inventory.setItem(0, a);
        inventory.setItem(1, b);
        inventory.setItem(2, c);

        // プレイヤーにインベントリを開く
        player.openInventory(inventory);
        return inventory;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getHolder() instanceof CustomInventoryHolder) {
            // カスタムインベントリの場合、アイテムの移動をキャンセル
            event.setCancelled(true);
        }
    }

    private static class CustomInventoryHolder implements org.bukkit.inventory.InventoryHolder {
        @Override
        public Inventory getInventory() {
            return null;
        }
    }

    @EventHandler
    public void Ybwisky(InventoryClickEvent event) {
        // クリックされたインベントリを取得
        Inventory clickedInventory = event.getClickedInventory();

        // クリックされたインベントリがプレイヤーのインベントリであるかどうかをチェック
        if (clickedInventory != null && clickedInventory.getType() == InventoryType.CHEST) {
            // クリックされたアイテムを取得
            ItemStack clickedItem = event.getCurrentItem();

            // クリックされたアイテムが空でないかつ名前が "Ybウィスキー" であるかどうかをチェック
            if (clickedItem != null && clickedItem.getType() != Material.AIR && clickedItem.hasItemMeta()) {
                ItemMeta meta = clickedItem.getItemMeta();
                if (meta.hasDisplayName() && meta.getDisplayName().equals("Ybウィスキー") && meta.hasLore()) {
                    /* お金が足りているかを判定
                    if(所持金　<　10000){
                      player.sendMessage("お金が足りません！);
                      } else {
                     */
                    // クリックされたアイテムが "Ybウィスキー" である場合、そのアイテムをプレイヤーのインベントリに追加
                    Player player = (Player) event.getWhoClicked();
                    if (player.getInventory().firstEmpty() != -1) {
                        player.getInventory().addItem(clickedItem);
                        // イベントをキャンセルする（インベントリの移動を防止）
                        event.setCancelled(true);
                    } else {
                        // プレイヤーのインベントリがいっぱいの場合は何もしない
                        player.sendMessage("インベントリがいっぱいです！");
                    }
                }
                if (meta.hasDisplayName() && meta.getDisplayName().equals("大石ウォッカ") && meta.hasLore()) {
                    /* お金が足りているかを判定
                    if(所持金　<　1000){
                      player.sendMessage("お金が足りません！);
                      } else {
                     */
                    // クリックされたアイテムが "大石ウォッカ" である場合、そのアイテムをプレイヤーのインベントリに追加
                    Player player = (Player) event.getWhoClicked();
                    if (player.getInventory().firstEmpty() != -1) {
                        player.getInventory().addItem(clickedItem);
                        // イベントをキャンセルする（インベントリの移動を防止）
                        event.setCancelled(true);
                    } else {
                        // プレイヤーのインベントリがいっぱいの場合は何もしない
                        player.sendMessage("インベントリがいっぱいです！");
                    }
                }
                if (meta.hasDisplayName() && meta.getDisplayName().equals("無限岩崎スカッシュ") && meta.hasLore()) {
                    /* お金が足りているかを判定
                    if(所持金　<　1000){
                      player.sendMessage("お金が足りません！);
                      } else {
                     */
                    // クリックされたアイテムが "無限岩崎スカッシュ" である場合、そのアイテムをプレイヤーのインベントリに追加
                    Player player = (Player) event.getWhoClicked();
                    if (player.getInventory().firstEmpty() != -1) {
                        player.getInventory().addItem(clickedItem);
                        // イベントをキャンセルする（インベントリの移動を防止）
                        event.setCancelled(true);
                    } else {
                        // プレイヤーのインベントリがいっぱいの場合は何もしない
                        player.sendMessage("インベントリがいっぱいです！");
                    }
                }
            }
        }
    }
    @EventHandler
    public void onPlayerConsumePotion(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        // Ybウィスキーを飲んだ場合かつYbウィスキーの名前が設定されている場合
        if (item.getType() == Material.POTION && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals("Ybウィスキー") && item.getItemMeta().hasLore()) {
            // ランダムな確率を生成
            double probability = Math.random();
            double threshold = 0.1; // 10%の確率

            // 確率に基づいてイベントを起こす
            if (probability < threshold) {
                Bukkit.getServer().broadcastMessage( ChatColor.YELLOW + player.getDisplayName() + "がYbウィスキーに当選しました！");
                player.sendMessage(ChatColor.YELLOW + "100000円" + "獲得しました！！");
                // playerにお金を増やす
            } else{
                player.sendMessage("ハズレ...");
            }
        }
        if (item.getType() == Material.POTION && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals("大石ウォッカ") && item.getItemMeta().hasLore()) {
            // ランダムな確率を生成
            double probability = Math.random();
            double threshold = 0.005; // 0.5%の確率

            // 確率に基づいてイベントを起こす
            if (probability < threshold) {
                Bukkit.getServer().broadcastMessage( ChatColor.YELLOW + player.getDisplayName() + "が大石ウォッカに当選しました！");
                player.sendMessage(ChatColor.YELLOW + "200000円" + ChatColor.WHITE+ "獲得しました！！");
                // playerにお金を増やす
            } else{
                player.sendMessage("ハズレ...");
            }
        }
        if (item.getType() == Material.POTION && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals("無限岩崎スカッシュ") && item.getItemMeta().hasLore()) {
            // ランダムな確率を生成
            double probability = Math.random();
            double threshold = 0.5; // 50%の確率

            // 確率に基づいてイベントを起こす
            if (probability < threshold) {
                player.sendMessage(ChatColor.YELLOW + "90000円" + ChatColor.WHITE+ "獲得しました！！");
                // playerにお金を増やす
                event.setCancelled(true);
            } else{
                player.sendMessage("ハズレ...");
            }
        }
    }
}
