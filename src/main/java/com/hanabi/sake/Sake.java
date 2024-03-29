package com.hanabi.sake;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
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
    Databace databace = new Databace();

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        databace.CloseConnection();
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Entity entity = event.getRightClicked();
        if (entity.getType() == EntityType.VILLAGER) {
            Villager villager = (Villager) entity;
            String villagerName = villager.getCustomName();
            if (villagerName != null) {
                if (villagerName.equals("はなびバーテンダー")) {
                    Player player = event.getPlayer();
                    createCustomInventory(player);
                }
            }
        }
    }

    private Inventory createCustomInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(new CustomInventoryHolder(), 9, "バーテンダー");

        //Ybウィスキーの追加
        ItemStack a = new ItemStack(Material.POTION);
        PotionMeta ameta = (PotionMeta) a.getItemMeta();
        ameta.setDisplayName("Ybウィスキー");
        ameta.setLore(Arrays.asList("購入金額　10000円", "当選金額　100000円", "期待度★☆☆☆☆","Yb社によって作られた熟練のウィスキーだ")); // ここで複数行の説明を設定
        ameta.setColor(Color.GRAY);
        a.setItemMeta(ameta);

        ItemStack b = new ItemStack(Material.POTION);
        PotionMeta bmeta = (PotionMeta) b.getItemMeta();
        bmeta.setDisplayName("大石ウォッカ");
        bmeta.setLore(Arrays.asList("購入金額　11014円", "当選金額　931014円", "期待度☆☆☆☆☆☆" ,"この酒は、大石の口の中で熟成してできた酒だそうだ...")); // ここで複数行の説明を設定
        bmeta.setColor(Color.YELLOW);
        b.setItemMeta(bmeta);

        ItemStack c = new ItemStack(Material.POTION);
        PotionMeta cmeta = (PotionMeta) c.getItemMeta();
        cmeta.setDisplayName("無限岩崎スカッシュ");
        cmeta.setLore(Arrays.asList("購入金額　100000円", "当選金額　90000円", "期待度？？？" ,"一度当たるともう一回続く...？")); // ここで複数行の説明を設定
        cmeta.setColor(Color.BLUE);
        c.setItemMeta(cmeta);

        ItemStack d = new ItemStack(Material.POTION);
        PotionMeta dmeta = (PotionMeta) c.getItemMeta();
        dmeta.setDisplayName("ハッシーハッピーブライトシャンパン777");
        dmeta.setLore(Arrays.asList("購入金額　77777円", "当選金額　1103000円",  "期待度☆☆☆☆☆","とある方々の魂が込められたシャンパンだ...")); // ここで複数行の説明を設定
        dmeta.setColor(Color.BLACK);
        d.setItemMeta(dmeta);

        ItemStack e = new ItemStack(Material.POTION);
        PotionMeta emeta = (PotionMeta) c.getItemMeta();
        emeta.setDisplayName("梅肉入り倉橋");
        emeta.setLore(Arrays.asList("購入金額　350円", "当選金額　700円", "期待度★★★☆☆","これは酒じゃなく普通に梅肉が入った倉橋では...？")); // ここで複数行の説明を設定
        emeta.setColor(Color.RED);
        e.setItemMeta(emeta);

        ItemStack f = new ItemStack(Material.POTION);
        PotionMeta fmeta = (PotionMeta) c.getItemMeta();
        fmeta.setDisplayName("中日ソーダ");
        fmeta.setLore(Arrays.asList("購入金額　1000円", "当選金額　1500円", "期待度★★★★☆","某中日ドラゴンズが稼ぐために考えられたソーダだ")); // ここで複数行の説明を設定
        fmeta.setColor(Color.AQUA);
        f.setItemMeta(fmeta);

        ItemStack g = new ItemStack(Material.POTION);
        PotionMeta gmeta = (PotionMeta) c.getItemMeta();
        gmeta.setDisplayName("速水ロック");
        gmeta.setLore(Arrays.asList("購入金額　500円", "当選金額　700円", "期待度★★★★☆","我らが音楽の教師速水が作り上げた臭すぎるロックだ")); // ここで複数行の説明を設定
        gmeta.setColor(Color.PURPLE);
        g.setItemMeta(gmeta);

        ItemStack h = new ItemStack(Material.POTION);
        PotionMeta hmeta = (PotionMeta) c.getItemMeta();
        hmeta.setDisplayName("八つ橋スプライト");
        hmeta.setLore(Arrays.asList("購入金額　10000円", "当選金額　9000円", "期待度？？？","一度当たるともう一回続く...？")); // ここで複数行の説明を設定
        hmeta.setColor(Color.MAROON);
        h.setItemMeta(hmeta);

        ItemStack i = new ItemStack(Material.POTION);
        PotionMeta imeta = (PotionMeta) c.getItemMeta();
        imeta.setDisplayName("堀端ミルク「生搾り」");
        imeta.setLore(Arrays.asList("購入金額　2000円", "当選金額　6000円", "期待度★★☆☆☆","貧〇だからこそ、ミルクが濃い！！（迫真）")); // ここで複数行の説明を設定
        imeta.setColor(Color.WHITE);
        i.setItemMeta(imeta);

        inventory.setItem(0, a);
        inventory.setItem(1, b);
        inventory.setItem(2, c);
        inventory.setItem(3, d);
        inventory.setItem(4, e);
        inventory.setItem(5, f);
        inventory.setItem(6, g);
        inventory.setItem(7, h);
        inventory.setItem(8, i);


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
    public void YbInventory(InventoryClickEvent event) {
        Inventory clickedInventory = event.getClickedInventory();

        if (clickedInventory != null && clickedInventory.getType() == InventoryType.CHEST) {
            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem != null && clickedItem.getType() != Material.AIR && clickedItem.hasItemMeta()) {
                ItemMeta meta = clickedItem.getItemMeta();
                if (meta.hasDisplayName() && meta.getDisplayName().equals("Ybウィスキー") && meta.hasLore()) {
                    Player player = (Player) event.getWhoClicked();
                    if (databace.CheckMoney(player) < 10000) {
                        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "所持金が不足しています");
                    } else {
                        if (player.getInventory().firstEmpty() != -1) {
                            player.getInventory().addItem(clickedItem);
                            event.setCancelled(true);
                            databace.AddMoney(player,-10000);
                        } else {
                            player.sendMessage("インベントリがいっぱいです！");
                        }
                    }
                }
                if (meta.hasDisplayName() && meta.getDisplayName().equals("大石ウォッカ") && meta.hasLore()) {
                    Player player = (Player) event.getWhoClicked();
                    if (databace.CheckMoney(player) < 11014) {
                        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "所持金が不足しています");
                    } else {
                        if (player.getInventory().firstEmpty() != -1) {
                            player.getInventory().addItem(clickedItem);
                            event.setCancelled(true);
                            databace.AddMoney(player,-11014);
                        } else {
                            player.sendMessage("インベントリがいっぱいです！");
                        }
                    }
                }
                if (meta.hasDisplayName() && meta.getDisplayName().equals("無限岩崎スカッシュ") && meta.hasLore()) {
                    Player player = (Player) event.getWhoClicked();
                    if (databace.CheckMoney(player) < 100000) {
                        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "所持金が不足しています");
                    } else {
                        if (player.getInventory().firstEmpty() != -1) {
                            player.getInventory().addItem(clickedItem);
                            event.setCancelled(true);
                            databace.AddMoney(player,-100000);
                        } else {
                            player.sendMessage("インベントリがいっぱいです！");
                        }
                    }
                }
                if (meta.hasDisplayName() && meta.getDisplayName().equals("ハッシーハッピーブライトシャンパン777") && meta.hasLore()) {
                    Player player = (Player) event.getWhoClicked();
                    if (databace.CheckMoney(player) < 77777) {
                        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "所持金が不足しています");
                    } else {
                        if (player.getInventory().firstEmpty() != -1) {
                            player.getInventory().addItem(clickedItem);
                            event.setCancelled(true);
                            databace.AddMoney(player,-77777);
                        } else {
                            player.sendMessage("インベントリがいっぱいです！");
                        }
                    }
                }
                if (meta.hasDisplayName() && meta.getDisplayName().equals("梅肉入り倉橋") && meta.hasLore()) {
                    Player player = (Player) event.getWhoClicked();
                    if (databace.CheckMoney(player) < 350) {
                        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "所持金が不足しています");
                    } else {
                        if (player.getInventory().firstEmpty() != -1) {
                            player.getInventory().addItem(clickedItem);
                            event.setCancelled(true);
                            databace.AddMoney(player,-350);
                        } else {
                            player.sendMessage("インベントリがいっぱいです！");
                        }
                    }
                }
                if (meta.hasDisplayName() && meta.getDisplayName().equals("中日ソーダ") && meta.hasLore()) {
                    Player player = (Player) event.getWhoClicked();
                    if (databace.CheckMoney(player) < 1000) {
                        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "所持金が不足しています");
                    } else {
                        if (player.getInventory().firstEmpty() != -1) {
                            player.getInventory().addItem(clickedItem);
                            event.setCancelled(true);
                            databace.AddMoney(player,-1000);
                        } else {
                            player.sendMessage("インベントリがいっぱいです！");
                        }
                    }
                }
                if (meta.hasDisplayName() && meta.getDisplayName().equals("速水ロック") && meta.hasLore()) {
                    Player player = (Player) event.getWhoClicked();
                    if (databace.CheckMoney(player) < 500) {
                        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "所持金が不足しています");
                    } else {
                        if (player.getInventory().firstEmpty() != -1) {
                            player.getInventory().addItem(clickedItem);
                            event.setCancelled(true);
                            databace.AddMoney(player,-500);
                        } else {
                            player.sendMessage("インベントリがいっぱいです！");
                        }
                    }
                }
                if (meta.hasDisplayName() && meta.getDisplayName().equals("八つ橋スプライト") && meta.hasLore()) {
                    Player player = (Player) event.getWhoClicked();
                    if (databace.CheckMoney(player) < 10000) {
                        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "所持金が不足しています");
                    } else {
                        if (player.getInventory().firstEmpty() != -1) {
                            player.getInventory().addItem(clickedItem);
                            event.setCancelled(true);
                            databace.AddMoney(player,-10000);
                        } else {
                            player.sendMessage("インベントリがいっぱいです！");
                        }
                    }
                }
                if (meta.hasDisplayName() && meta.getDisplayName().equals("堀端ミルク「生搾り」") && meta.hasLore()) {
                    Player player = (Player) event.getWhoClicked();
                    if (databace.CheckMoney(player) < 2000) {
                        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "所持金が不足しています");
                    } else {
                        if (player.getInventory().firstEmpty() != -1) {
                            player.getInventory().addItem(clickedItem);
                            event.setCancelled(true);
                            databace.AddMoney(player,-2000);
                        } else {
                            player.sendMessage("インベントリがいっぱいです！");
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerConsumePotion(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item.getType() == Material.POTION && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals("Ybウィスキー") && item.getItemMeta().hasLore()) {
            double probability = Math.random();
            double threshold = 0.1; // 10%の確率

            if (probability < threshold) {
                Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + player.getDisplayName() + "がYbウィスキーに当選しました！");
                player.sendMessage(ChatColor.YELLOW + "100000円" + "獲得しました！！");
                databace.AddMoney(player,100000);

            } else {
                player.sendMessage("ハズレ...");
            }
        }
        if (item.getType() == Material.POTION && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals("大石ウォッカ") && item.getItemMeta().hasLore()) {
            double probability = Math.random();
            double threshold = 0.012; // 1.2%の確率

            if (probability < threshold) {
                Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + player.getDisplayName() + "が大石ウォッカに当選しました！");
                player.sendMessage(ChatColor.YELLOW + "931014円" + ChatColor.WHITE + "獲得しました！！");
                databace.AddMoney(player,931014);

            } else {
                player.sendMessage("ハズレ...");
            }
        }
        if (item.getType() == Material.POTION && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals("無限岩崎スカッシュ") && item.getItemMeta().hasLore()) {
            double probability = Math.random();
            double threshold = 0.5; // 50%の確率

            // 確率に基づいてイベントを起こす
            if (probability < threshold) {
                player.sendMessage(ChatColor.YELLOW + "90000円" + ChatColor.WHITE + "獲得しました！！");
                databace.AddMoney(player,90000);
                event.setCancelled(true);
            } else {
                player.sendMessage("ハズレ...");
            }
        }
        if (item.getType() == Material.POTION && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals("ハッシーハッピーブライトシャンパン777") && item.getItemMeta().hasLore()) {
            double probability = Math.random();
            double threshold = 0.07; // 7%の確率

            if (probability < threshold) {
                Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + player.getDisplayName() + "がハッシーハッピーブライトシャンパン777に当選しました！");
                player.sendMessage(ChatColor.YELLOW + "1103000円" + ChatColor.WHITE + "獲得しました！！");
                databace.AddMoney(player,1103000);

            } else {
                player.sendMessage("ハズレ...");
            }
        }
        if (item.getType() == Material.POTION && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals("梅肉入り倉橋") && item.getItemMeta().hasLore()) {
            double probability = Math.random();
            double threshold = 0.5; // 50%の確率

            if (probability < threshold) {
                player.sendMessage("当たり！");
                player.sendMessage(ChatColor.YELLOW + "700円" + ChatColor.WHITE + "獲得しました！！");
                databace.AddMoney(player,700);

            } else {
                player.sendMessage("ハズレ...");
            }
        }
        if (item.getType() == Material.POTION && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals("中日ソーダ") && item.getItemMeta().hasLore()) {
            double probability = Math.random();
            double threshold = 0.67; // 67%の確率

            if (probability < threshold) {
                player.sendMessage("当たり！");
                player.sendMessage(ChatColor.YELLOW + "1500円" + ChatColor.WHITE + "獲得しました！！");
                databace.AddMoney(player,1500);

            } else {
                player.sendMessage("ハズレ...");
            }
        }
        if (item.getType() == Material.POTION && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals("速水ロック") && item.getItemMeta().hasLore()) {
            double probability = Math.random();
            double threshold = 0.71; // 71%の確率

            if (probability < threshold) {
                player.sendMessage("当たり！");
                player.sendMessage(ChatColor.YELLOW + "700円" + ChatColor.WHITE + "獲得しました！！");
                databace.AddMoney(player,700);

            } else {
                player.sendMessage("ハズレ...");
            }
        }
        if (item.getType() == Material.POTION && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals("八つ橋スプライト") && item.getItemMeta().hasLore()) {
            double probability = Math.random();
            double threshold = 0.5; // 50%の確率

            // 確率に基づいてイベントを起こす
            if (probability < threshold) {
                player.sendMessage(ChatColor.YELLOW + "9000円" + ChatColor.WHITE + "獲得しました！！");
                databace.AddMoney(player,9000);
                event.setCancelled(true);
            } else {
                player.sendMessage("ハズレ...");
            }
        }
        if (item.getType() == Material.POTION && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals("堀端ミルク「生搾り」") && item.getItemMeta().hasLore()) {
            double probability = Math.random();
            double threshold = 0.333; // 33.3%の確率

            if (probability < threshold) {
                player.sendMessage("当たり！");
                player.sendMessage(ChatColor.YELLOW + "6000円" + ChatColor.WHITE + "獲得しました！！");
                databace.AddMoney(player,6000);

            } else {
                player.sendMessage("ハズレ...");
            }
        }
    }
}