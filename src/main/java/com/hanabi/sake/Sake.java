package com.hanabi.sake;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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

        ItemStack regenerationPotion = new ItemStack(Material.POTION);
        regenerationPotion.setDurability((short) 8193); // 再生のポーションのデータ値
        ItemMeta meta = regenerationPotion.getItemMeta();
        meta.setDisplayName("Ybウィスキー" );
        meta.setLore(Arrays.asList("購入金額　10000円", "当選金額　200000円")); // ここで複数行の説明を設定
        regenerationPotion.setItemMeta(meta);

        // インベントリに再生のポーションを追加
        inventory.setItem(0, regenerationPotion);

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
                if (meta.hasDisplayName() && meta.getDisplayName().equals("Ybウィスキー")) {
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
            }
        }
    }
}
