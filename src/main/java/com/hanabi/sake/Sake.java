package com.hanabi.sake;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
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
        inventory.setItem(4, regenerationPotion);

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
    public void onPlayerInteract(PlayerInteractEntityEvent event) {
        if (event.getRightClicked().getType() == EntityType.VILLAGER) {
            Villager villager = (Villager) event.getRightClicked();
            if (villager.getCustomName() != null && villager.getCustomName().equals("バーテンダー")) {
                // プレイヤーがバーテンダーという名前の村人を右クリックした場合、取引を無効にする
                event.setCancelled(true);
                Player player = event.getPlayer();
                player.sendMessage("このバーテンダーは取引できません！");
            }
        }
    }
}
