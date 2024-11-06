package GameAchieve;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 创建主题
        AttackCardManager attackCardManager = new AttackCardManager();
        SkillCardManager skillCardManager = new SkillCardManager();
        AbilityCardManager abilityCardManager = new AbilityCardManager();
        RelicManager relicManager=new RelicManager();


        // 角色选择

        System.out.println("Choose your character:");
        System.out.println("1: Silent");
        System.out.println("2: Ironclad");
        int characterChoice = scanner.nextInt();
        Character playerCharacter;

        if (characterChoice == 1) {
            playerCharacter = new Character("Silent", 70, 2);
            AttackCardManager.addDefaultStrikeCards(); // Silent的牌组
            SkillCardManager.addDefaultSkillCards();
            // 添加Silent的特定牌
            // 添加其他攻击牌
            AttackCardManager.addAttackCard(new SilentAttackCard("knife", "Use a small knife to harm your enemy.(cost : 0 baseDamage : 4)\n", 0, 4, null, null,true));
            AttackCardManager.addAttackCard(new SilentAttackCard("Neutralization", "Deal damage to the enemy .(cost : 0 baseDamage : 2)\n", 0, 2, null, null,false));
            AttackCardManager.addAttackCard(new SilentAttackCard("Double Strike", "An attack card that hits twice.(cost : 2 baseDamage : 2 * 3)\n", 2, 6, null, null,false));
            AttackCardManager.addAttackCard(new SilentAttackCard("Super Strike", "A very powerful blow, but it will weaken oneself after being hit.(cost : 4 baseDamage : 50)\n", 4, 50, null, "Weakness +2",false));

            // 添加其他技能牌
            SkillCardManager.addSkillCard(new SilentSkillCard("Perfect defend", "A defense card which can defend more.(cost : 1 Defend : 10)\n", 1, 10, null, null,false));
            SkillCardManager.addSkillCard(new SilentSkillCard("Consolidate", "Make your defend value double.(cost : 2)\n", 2, 0, null, null,false));

            // 添加其他能力牌
            AbilityCardManager.addAbilityCard(new SilentAbilityCard("Demon Form", "Let you increase three powers.(cost : 3)\n", 3, 0, "Strength +3", null,true));

            // ... 继续添加其他牌
        } else {
            playerCharacter = new Character("Ironclad", 80, 0);
            AttackCardManager.addDefaultStrikeCards(); // Ironclad的牌组
            SkillCardManager.addDefaultSkillCards();
            // 添加Ironclad的特定牌
            // 添加其他攻击牌
            AttackCardManager.addAttackCard(new IroncladAttackCard("Heavy Strike", "A powerful attack card.(cost : 3 baseDamage : 35)\n", 3, 35, null, null,false));
            AttackCardManager.addAttackCard(new IroncladAttackCard("Quick Strike", "A fast attack card.(cost : 1 baseDamage : 4)\n", 1, 4, null, null,false));
            AttackCardManager.addAttackCard(new IroncladAttackCard("Double Strike", "An attack card that hits twice.(cost : 2 baseDamage : 2 * 3)\n", 2, 6, null, null,false));
            AttackCardManager.addAttackCard(new IroncladAttackCard("Super Strike", "A very powerful blow, but it will weaken oneself after being hit.(cost : 4 baseDamage : 50)\n", 4, 50, null, "Weakness +2",false));

            // 添加其他技能牌
            SkillCardManager.addSkillCard(new IroncladSkillCard("Perfect defend", "A defense card which can defend more.(cost : 1 Defend : 10)\n", 1, 10, null, null,false));
            SkillCardManager.addSkillCard(new IroncladSkillCard("Consolidate", "Make your defend value double.(cost : 2)\n", 2, 0, null, null,false));

            // 添加其他能力牌
            AbilityCardManager.addAbilityCard(new IroncladAbilityCard("Demon Form", "Let you increase three powers.(cost:3)\n", 3, 0, "Strength +5", null,true));


            // ... 继续添加其他牌
        }
        Monster enemy = Monster.generateRandomMonster();

        //将卡牌添加进卡组
        playerCharacter.getDeck().addAll(AttackCardManager.getAttackCards());
        playerCharacter.getDeck().addAll(AbilityCardManager.getAbilityCards());
        playerCharacter.getDeck().addAll(SkillCardManager.getSkillCards());

        // 添加默认的“打击”牌
        AttackCardManager.addDefaultStrikeCards();

        // 添加默认的“防御”牌
        SkillCardManager.addDefaultSkillCards();

        //初始化抽牌堆
        playerCharacter.initializeDrawPile();

        // Initial relic selection
        try {
            System.out.println("Choose one of the following relics:");
            List<Relic> initialRelics = RelicManager.getRandomRelics(3);
            for (int i = 0; i < initialRelics.size(); i++) {
                System.out.println((i + 1) + ". " + initialRelics.get(i).getName());
            }

            int relicChoice = -1;
            boolean validInput = false;
            while (!validInput) {
                try {

                    System.out.print("Enter the number of the relic you want to choose (1-3): ");
                    relicChoice = scanner.nextInt() - 1;
                    if (relicChoice >= 0 && relicChoice < initialRelics.size()) {
                        validInput = true;
                    } else {
                        System.out.println("Invalid input. Please enter a number between 1 and 3.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    scanner.next(); // 清除输入缓冲区中的无效输入
                }
            }
            Relic chosenRelic = initialRelics.get(relicChoice);
            chosenRelic.applyEffect(playerCharacter);
            playerCharacter.addRelicToCage(chosenRelic); // 将选择的遗物添加到遗物笼中

            if (chosenRelic.getName().equalsIgnoreCase("Sword")) {
                Relic strengthRelic = new SwordRelic();
                strengthRelic.applyEffect(playerCharacter);
            } else if (chosenRelic.getName().equalsIgnoreCase("Book")) {
                Relic energyRelic = new BookRelic();
                energyRelic.applyEffect(playerCharacter);
            }
            System.out.println(playerCharacter.getName() + " chose: " + chosenRelic.getName());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("An error occurred while selecting the relic. Please try again.");
        }

        // 打印遗物笼中的遗物
        System.out.println("Relics in RelicCage:");
        for (String relicName : playerCharacter.getRelicCage()) {
            System.out.println(relicName);
        }


        System.out.println("----------------The Fight!----------------");
        displayMonsterStats(enemy);
        System.out.println("------------------------------------------");
        boolean continueGame = true; // 添加一个标志变量来控制循环的结束
        boolean isPlayerTurn = true; // 添加一个标志变量来控制回合

        while (continueGame) {
            if (isPlayerTurn) {
                // 玩家回合
                System.out.println("-----------------Your turn!---------------------");
                playerCharacter.initializeDrawPile();
                displayCharacterStats(playerCharacter);
                List<Card> drawnCards = drawRandomCards(5);
                int choice=-1;
                do {
                    System.out.println("Your hand:");
                    for (int i = 0; i < drawnCards.size(); i++) {
                        System.out.println((i + 1) + ": " + drawnCards.get(i).getName() + " - " + drawnCards.get(i).getDescription());
                    }


                    System.out.print("Choose a card to play (1-" + drawnCards.size() + "), enter 0 to end your turn and reset energy, or enter 50 to show your Card Library: ");
                    System.out.println("enter 80 to check the viewDiscardPile");
                    System.out.println("enter 90 to check the viewDrawPile");
                    System.out.println("enter 100 to check the viewExhaustPile");
                    try {
                        choice = scanner.nextInt(); // 尝试读取用户输入的整数
                        if(choice ==80){
                            playerCharacter.viewDiscardPile();
                        }else if(choice == 90){

                            playerCharacter.viewDrawPile();
                        }else if(choice ==100){
                            playerCharacter.viewExhaustPile();
                        } else if (choice == 50) {
                            displayCardLibrary();
                        } else if (choice >= 1 && choice <= drawnCards.size()) {
                            Card selectedCard = drawnCards.remove(choice - 1);
                            System.out.println("Playing card: " + selectedCard.getName());
                            selectedCard.play(playerCharacter, enemy);
                            displayCharacterStats(playerCharacter);
                            System.out.println("Your hand:");
                            // 检查怪物是否死亡
                            if (enemy.isDead()) {
                                System.out.println("The monster is defeated!");
                                playerCharacter.resetEnergy();          // 重置能量
                                drawnCards = drawRandomCards(5); // 重新抽牌
                                refreshCards();                        // 重置所有牌的使用状态
                                playerCharacter.resetStrength();      //重置力量
                                break;
                            }
                            if(characterChoice==1){
                                // 根据卡牌类型进行弃牌或消耗
                                if (selectedCard instanceof SilentAbilityCard) {
                                    SilentAbilityCard abilityCard = (SilentAbilityCard) selectedCard;
                                    if (abilityCard.isExhaust()) {
                                        playerCharacter.exhaustCard(abilityCard);
                                    } else {
                                        playerCharacter.discardCard(abilityCard);
                                    }
                                } else if(selectedCard instanceof SilentAttackCard){
                                    SilentAttackCard attackCard =(SilentAttackCard) selectedCard;
                                    if(attackCard.isExhaust()){
                                        playerCharacter.exhaustCard(attackCard);
                                    }else{
                                        playerCharacter.discardCard(attackCard);
                                    }
                                }else{
                                    SilentSkillCard skillCard =(SilentSkillCard) selectedCard;
                                    if(skillCard.isExhaust()){
                                        playerCharacter.exhaustCard(skillCard);
                                    }else{
                                        playerCharacter.discardCard(skillCard);
                                    }
                                }
                            }else{
                                // 根据卡牌类型进行弃牌或消耗
                                if (selectedCard instanceof IroncladAbilityCard) {
                                    IroncladAbilityCard abilityCard = (IroncladAbilityCard) selectedCard;
                                    if (abilityCard.isExhaust()) {
                                        playerCharacter.exhaustCard(abilityCard);
                                    } else {
                                        playerCharacter.discardCard(abilityCard);
                                    }
                                } else if(selectedCard instanceof IroncladAttackCard){
                                    IroncladAttackCard attackCard =(IroncladAttackCard) selectedCard;
                                    if(attackCard.isExhaust()){
                                        playerCharacter.exhaustCard(attackCard);
                                    }else{
                                        playerCharacter.discardCard(attackCard);
                                    }
                                }else{
                                    IroncladSkillCard skillCard =(IroncladSkillCard) selectedCard;
                                    if(skillCard.isExhaust()){
                                        playerCharacter.exhaustCard(skillCard);
                                    }else{
                                        playerCharacter.discardCard(skillCard);
                                    }
                                }
                            }


                        } else if (choice != 0) {
                            System.out.println("Invalid choice. Please try again.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                        scanner.next(); // 清除输入缓冲区中的无效输入
                    }
                } while (choice != 0);

                // 如果玩家选择结束回合（输入0），重置能量并重新抽牌
                if (choice == 0) {
                    System.out.println("Ending your turn and resetting energy...");
                    playerCharacter.resetEnergy(); // 重置能量
                    drawnCards = drawRandomCards(5); // 重新抽牌
                }

                // 显示状态信息
                displayCharacterStats(playerCharacter);
                displayMonsterStats(enemy);

                if (enemy.isDead()) {
                    //在这里创建一个与Relic类相关的方法。
                    RandomRelic(playerCharacter);   //随机生成遗物
                    refreshCards();                 // 重置所有牌的使用状态
                    playerCharacter.resetStrength();      //重置力量
                    playerCharacter.resetEnergy(); // 重置能量
                    drawnCards = drawRandomCards(5); // 重新抽牌

                    if (continueGame) {
                        // 生成新的怪物
                        enemy = Monster.generateRandomMonster();
                        System.out.println("-----------------------------------------------------------------------");
                        System.out.println("----------------------------Next Battle!--------------------------------");
                        System.out.println("A new MONSTER appears!!!");
                    }
                }


                // 切换到怪物回合
                isPlayerTurn = false;
            } else {
                // 怪物回合
                System.out.println("-----------------The enemy's turn!---------------------");
                enemy.attack(playerCharacter); // 怪物攻击玩家
                if (playerCharacter.isDead()) {
                    System.out.println("You have been defeated!");
                    System.out.println("Game Over");
                    continueGame = false; // 结束游戏
                }
                System.out.println("------------------------------------------------------");

                // 显示状态信息
                displayCharacterStats(playerCharacter);
                displayMonsterStats(enemy);

                // 切换到玩家回合
                isPlayerTurn = true;
            }

            // 检查游戏是否结束
            if (playerCharacter.isDead()) {
                System.out.println("You have been defeated!");
                System.out.println("Game Over");
                continueGame = false;
            } else if (enemy.isDead()) {
                System.out.println("The monster is defeated!");
                continueGame = false;
            }
        }
        scanner.close();
    }

    // 显示玩家的状态信息
    public static void displayCharacterStats(Character character) {
        System.out.println("Player" + character.getName() + " stats:");
        System.out.println("Health            : " + character.getHealth());
        System.out.println("Block             : " + character.getEffectValue());
        System.out.println("Energy            : "  + character.getEnergy() );
        System.out.println("Strength          : " + character.getStrength());
        System.out.println("Buff Effect       : " + character.getBuffEffect());
        System.out.println("Debuff Effect     : " + character.getDebuffEffect());
    }

    // 显示怪物的状态信息
    public static void displayMonsterStats(Monster monster) {
        System.out.println("----------------Monster----------------");
        System.out.println("Monster " + monster.getName() + " stats:");
        System.out.println("Health             : " + monster.getHealth());
        System.out.println("Strength           : " + monster.getStrength());
        System.out.println("Debuff Effect      : " + monster.getDebuffEffect());
        // 如果怪物有其他状态信息，可以在这里添加
    }

    // 实现读取牌组的功能
    public static void displayCardLibrary() {
        System.out.println("---------------Your Card Library---------------");

        System.out.println("Attack Cards:");
        for (Card card : AttackCardManager.getAttackCards()) {
            System.out.println(card.getName() + " - " + card.getDescription());
        }

        System.out.println("Skill Cards:");
        for (Card card : SkillCardManager.getSkillCards()) {
            System.out.println(card.getName() + " - " + card.getDescription());
        }

        System.out.println("Ability Cards:");
        for (Card card : AbilityCardManager.getAbilityCards()) {
            System.out.println(card.getName() + " - " + card.getDescription());
        }

        System.out.println("-----------------------------------------------");
    }

    // 随机抽取指定数量的牌，确保不重复
    public static List<Card> drawRandomCards(int count) {
        List<Card> allCards = new ArrayList<>();
        allCards.addAll(AttackCardManager.getAttackCards());
        allCards.addAll(SkillCardManager.getSkillCards());
        allCards.addAll(AbilityCardManager.getAbilityCards());

        List<Card> drawnCards = new ArrayList<>();
        Set<Card> drawnSet = new HashSet<>();       //创建一个 HashSet 集合，用于存储已经抽取的卡牌，以确保这些卡牌不会重复

        if (allCards.isEmpty() || count > allCards.size()) {
            return drawnCards;
        }

        Random random = new Random();
        while (drawnCards.size() < count) {
            int index = random.nextInt(allCards.size());
            Card card = allCards.get(index);
            if (!drawnSet.contains(card)) {
                drawnCards.add(card);
                drawnSet.add(card);
            }
        }

        return drawnCards;
    }
    // 重置所有牌的使用状态
    public static void refreshCards() {
        List<Card> allCards = new ArrayList<>();
        allCards.addAll(AttackCardManager.getAttackCards());
        allCards.addAll(SkillCardManager.getSkillCards());
        allCards.addAll(AbilityCardManager.getAbilityCards());

        for (Card card : allCards) {
            card.setUsed(false);
        }
    }
    public static void RandomRelic(Character playerCharacter){
        Random random = new Random();
        System.out.println("-----------------------Reward Part Of Relic-----------------------");
        if (random.nextBoolean()) {
            Relic droppedRelic = RelicManager.getRandomDrop();
            if (droppedRelic != null) {
                droppedRelic.applyEffect(playerCharacter);
                System.out.println("Congratulation!" + playerCharacter.getName() + " found: " + droppedRelic.getName() + ".");
                System.out.println("-----------------------------------------------------------------------");
                if (droppedRelic.getName().equalsIgnoreCase("Sword")) {
                    Relic SwordRelic = new SwordRelic();
                    SwordRelic.applyEffect(playerCharacter);
                } else if (droppedRelic.getName().equalsIgnoreCase("Book")) {
                    Relic BookRelic = new BookRelic();
                    BookRelic.applyEffect(playerCharacter);
                } else if (droppedRelic.getName().equalsIgnoreCase("Ring")) {
                    Relic RingRelic = new RingRelic();
                    RingRelic.applyEffect(playerCharacter);
                }else if (droppedRelic.getName().equalsIgnoreCase("Shield")) {
                    Relic ShieldRelic = new ShieldRelic();
                    ShieldRelic.applyEffect(playerCharacter);
                }
            } else {
                System.out.println("Sorry,but there is no relic dropped!");
                System.out.println("-----------------------------------------------------------------------");
            }
        }else {
            System.out.println("Sorry,but there is no relic dropped!");
            System.out.println("-----------------------------------------------------------------------");
        }
    }
}