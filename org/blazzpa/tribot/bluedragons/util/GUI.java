package scripts.org.blazzpa.tribot.bluedragons.util;

import scripts.org.blazzpa.tribot.bluedragons.bBlueDragonKiller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class GUI extends JFrame {

    private bBlueDragonKiller instance;
    private ArrayList<String> itemsTake = new ArrayList<String>();
    private ArrayList<String> itemsIgnored = new ArrayList<String>();

    final JList<String> ignoreList = new JList<String>();
    final JList<String> takeList = new JList<String>();

    public GUI(bBlueDragonKiller instance) {
        this.instance = instance;
        setSize(630, 450);
        setLocationRelativeTo(getParent());
        initComponents();
    }

    private void initComponents() {
        final JLabel foodLabel = new JLabel("Please select a food type:");
        final JLabel hpLabel = new JLabel("Please enter at what hitpoints to heal at:");
        final JLabel howManyLabel = new JLabel("Please select how many food to take:");
        final JLabel lootListLabel = new JLabel("Loot list");
        final JLabel takeLabel = new JLabel("Take");
        final JLabel ignoreLabel = new JLabel("Ignore");
        final JScrollPane ignoreScroll = new JScrollPane();
        final JScrollPane takeScroll = new JScrollPane();
        final JButton bAdd = new JButton("»");
        final JButton bRemove = new JButton("«");
        final JButton bStart = new JButton("Start Script");
        final JComboBox<String> cbFoodType = new JComboBox<String>();
        final JCheckBox cUsePotion = new JCheckBox("Use ranging potions?");
        final JTextField eatAtText = new JTextField("20");
        final JTextField howManyText = new JTextField("4");

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);


        bAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ignoreList.getSelectedIndex() != -1) {
                    itemsIgnored.add(ignoreList.getSelectedValue());
                    itemsTake.remove(ignoreList.getSelectedValue());
                    setIgnoreListModel();
                    setTakeListModel();
                }
            }
        });

        bRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (takeList.getSelectedIndex() != -1) {
                    itemsTake.add(takeList.getSelectedValue());
                    itemsIgnored.remove(takeList.getSelectedValue());
                    setIgnoreListModel();
                    setTakeListModel();
                }
            }
        });

        bStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int eatAt = -1, amtOfFood = -1;
                try {
                    eatAt = Integer.parseInt(eatAtText.getText());
                    amtOfFood = Integer.parseInt(howManyText.getText());
                } catch (final NumberFormatException nfe) {
                    nfe.printStackTrace();
                    instance.terminate = true;
                }
                int foodId = instance.methods.getFoodId((String) cbFoodType.getSelectedItem());
                if (foodId == -1) {
                    instance.terminate = true;
                }
                instance.settings.minimumHitpoints = eatAt;
                instance.settings.minimumFoodCount = amtOfFood;
                instance.settings.selectedFoodID = foodId;
                instance.settings.selectedLoot = instance.methods.getLootIDArray(itemsTake);
                instance.settings.drinkRangingPotion = cUsePotion.isSelected();
                instance.setGUIDone(true);
                dispose();
            }
        });
        cbFoodType.setModel(new DefaultComboBoxModel<String>(getSupportedFoods()));

        ignoreScroll.setViewportView(ignoreList);
        takeScroll.setViewportView(takeList);
        addItems();
        setIgnoreListModel();
        setTakeListModel();

        ignoreList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        takeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(bStart, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(lootListLabel)
                                                        .addComponent(cUsePotion)
                                                        .addComponent(howManyText, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(eatAtText, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(hpLabel)
                                                        .addComponent(foodLabel)
                                                        .addComponent(cbFoodType, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(ignoreScroll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(bAdd)
                                                                        .addComponent(bRemove)))
                                                        .addComponent(howManyLabel)
                                                        .addComponent(takeLabel))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(ignoreLabel)
                                                        .addComponent(takeScroll, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(foodLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbFoodType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hpLabel)
                                .addGap(1, 1, 1)
                                .addComponent(eatAtText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(howManyLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(howManyText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cUsePotion)
                                .addGap(6, 6, 6)
                                .addComponent(lootListLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(ignoreLabel)
                                        .addComponent(takeLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(10, 10, 10)
                                                                .addComponent(bAdd)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(bRemove))
                                                        .addComponent(ignoreScroll, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addComponent(bStart)
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(takeScroll, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))))
        );

    }

    public String[] getSupportedFoods() {
        String[] foods = new String[FoodEnum.values().length];
        int idx = 0;
        for (FoodEnum fe : FoodEnum.values()) {
            foods[idx] = fe.name();
            idx++;
        }
        return foods;
    }

    public void addItems() {
        for (LootEnum le : LootEnum.values()) {
            itemsTake.add(le.name());
        }
    }

    public void setIgnoreListModel() {
        Collections.sort(itemsTake);
        ignoreList.setModel(new DefaultListModel<String>() {
            String[] t = new String[itemsTake.size()];
            String[] strings = itemsTake.toArray(t);

            public int getSize() {
                return strings.length;
            }

            public String getElementAt(int i) {
                return strings[i];
            }
        });
    }

    public void setTakeListModel() {
        Collections.sort(itemsIgnored);
        takeList.setModel(new DefaultListModel<String>() {
            String[] t = new String[itemsIgnored.size()];
            String[] strings = itemsIgnored.toArray(t);

            public int getSize() {
                return strings.length;
            }

            public String getElementAt(int i) {
                return strings[i];
            }
        });
    }

}

