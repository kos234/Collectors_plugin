<div style="font-size: 1.5em;">
<h2>
Collectors plugin - плагин для продвинутых коллекций
</h2>
<h4>beta version 1.0.0</h4>
<div style="color:#fff;">Документация со временем будет дополняться картинками и более подробной информацией</div>
<br><br>
Плагин работает с использованием кастомных nbt тегов. Соответственно, чтобы использовать какую-то функцию нужно выдать соответствующий тег.

Некоторые функции имеют куллдаун использования, который можно указывать в тегах

Плагин сделан на основе ядра сервера spigot 1.16.5 и поддержкой NMS разных версий. (СКОРО)

<h4>Функции плагина:</h4>
<ul>
<li>
Призыв молний. Тег - <code>CPLightning:int</code>
<br>
<br>
При клике правой кнопки мыши по воздуху, призывает молнию по координатам блока, на который смотрит игрок.
<br>
В качестве параметра выступает целое число - кулдаун перед следующим использованием в секундах.
</li>
<br>
<li>
Телепортация на несколько блоков – <code>CPTeleport:{Distance:int, CD:int}</code>
<br>
<br>
При клике правой кнопки мыши по воздуху, телепортирует игрока в пределах указанной дистанции с учетом указанного кулдауна
</li>
<br>
<li>
Фейерверк - CPFirework:{Meta:***,CD:int}<br><br>
При клике правой кнопки мыши по воздуху, запускает из координат игрока фейерверк с указанными параметрами. Если в Meta указано слово Random, фейерверк будет генерироваться случайным образом.<br>
<br>Meta для создания фейерверков: <code>{MainColor:[1,2],FadeColor:[3,4],Type:2,Power:1,Effect:1}</code>, где <code>MainColor</code> – основной цвет заряда, <code>FadeColor</code> – цвет выцветания, Type – тип фейерверка, Power – сила от 1 до 3, Effect либо 0 – простой, либо 1 – частицы больше и более заметно падают
<br><br>Цвета: Белый – 0, Серебряный – 1, Серый – 2, Черный – 3, Красный – 4, Тёмно-бордовый – 5, Желтый – 6, Оливковый – 7, Лаймовый – 8, Зеленый – 9, Цианидовский – 10, какой-то TEAL – 11, Голубой, как я, - 12, NAVY – 13, Фуксия – 14, Фиолетовый – 15, Оранжевый – 16. <br><br>Типы: Шар – 0, Большой шар – 1, Звезда – 2, Взрыв – 3, Крипер - 4 
</li>
<br>
<li>
Анимированная смена цвета кожаной брони, если она надета на игрока - <code>CPAnimationColor:{Colors:[hex],Speed:int,Stage:int}</code>
<br><br>
Перекрашивает броню в указанные <strong>HEX</strong> цвета <strong>БЕЗ СИМВОЛА РЕШЁТКИ</strong>. Параметр <strong>Speed</strong> отвечает за задержку перед сменой одним цветом, указывается число секунд в тиках <i>(1 секунда = 20 тиков)</i>. <br>Параметр <strong>Stage</strong> отвечает за плавную смену цвета – чем он больше, тем плавнее, но есть пределы, при которых цвета начинают смешиваться друг с другом. Если плавная смена цвета не подразумевается, этот параметр можно не указывать
</li>
<br>
<li>
Снежная пушка – <code>CPSnowball:int</code><br><br>
При клике правой кнопки мыши по воздуху, выстреливает снежком по направлению взгляда игрока. В качестве параметра выступает кулдаун перед повторным использованием
</li>
<br><br>
<li>
Яйцемёт – <code>CPEggs:int</code><br><br>
При клике правой кнопки мыши по воздуху, выстреливает яйцом по направлению взгляда игрока. В качестве параметра выступает кулдаун перед повторным использованием
</li>
<br><br>
<li>
Пушка со стрелами – <code>CPArrow:int</code><br><br>
При клике правой кнопки мыши по воздуху, выстреливает стрелой по направлению взгляда игрока. В качестве параметра выступает кулдаун перед повторным использованием
</li>
<br><br>
<li>
Эффект, когда предмет в главной руке, - <code>CPEffectInMainHand:{Particles:int, Data:[{Id:STRING,Lvl:int}]}</code>
<br><br>
Выдаёт игроку указанные эффекты, когда предмет находится у игрока в главной руке. Particles – показ частиц, где -1 – скрыть частицы, 0 – полупрозрачные частицы, 1 – обычные чатицы. Lvl – уровень эффекта, Id – текстовое айди эффекта: SPEED, SLOW, FAST_DIGGING, SLOW_DIGGING, INCREASE_DAMAGE, HEAL, HARM, JUMP, CONFUSION, REGENERATION, DAMAGE_RESISTANCE, FIRE_RESISTANCE, WATER_BREATHING, INVISIBILITY, BLINDNESS, NIGHT_VISION, HUNGER, WEAKNESS, POISON, WITHER, HEALTH_BOOST, ABSORPTION, SATURATION, GLOWING, LEVITATION, LUCK, UNLUCK, SLOW_FALLING, CONDUIT_POWER, DOLPHINS_GRACE, BAD_OMEN, HERO_OF_THE_VILLAGE
</li>
<br><br>
<li>
Эффект, когда предмет во второй руке, - <code>CPEffectInSecondHand:{Particles:int, Data:[{Id:STRING,Lvl:int}]}</code>
<br><br>
Выдаёт игроку указанные эффекты, когда предмет находится у игрока в главной руке.
</li>
<br><br>
<li>
Эффект, когда предмет надет на игрока, - <code>CPEffectInArmor:{Particles:int, Data:[{Id:STRING,Lvl:int}]}</code>
<br><br>
Выдаёт игроку указанные эффекты, когда предмет надет на игрока.
</li>
<br><br>
<li>
Эффект, когда предмет находится в инвентаре, - <code>CPEffectInInventory:{Particles:int, Data:[{Id:STRING,Lvl:int}]}</code>
<br><br>
Выдаёт игроку указанные эффекты, когда предмет находится у игрока в инвентаре.
</li>
<br><br>
<li>
Ломание нескольких блоков – <code>CPBreak:{Size:int(X)int(X)int,BreakItem:int}</code>
<br><br>
Ломает блоки в указанном радиусе. Если числа будут нечётные, к ним прибавляется единица. Параметром <strong>Size</strong> указывается радиус X Y Z относительно направления взгляда игрока через английский символ <strong>X</strong>. Параметр <strong>BreakItem</strong> отвечает за поломку предмета при выкапывании области, 0 – область не будет влиять на прочность предмета, 1 – будет ещё как
</li>
<br><br>
<li>
Зачарование автоплавка - <code>CPAutoMelting:int</code>
<br><br>
Автоматически плавит каменные пароды и руды, который способны на переплавку. Число после тега нужно, чтобы тег заработал, оно не оказывает никакого влияния
</li>
<br><br>
<li>
Зачарование телекинез - <code>CPTelekinesis:int</code>
<br><br>
Автоматически переносит все добываемые материалы в инвентарь игроку. Число после тега нужно, чтобы тег заработал, оно не оказывает никакого влияния
</li>
<br><br>
<li>
Зачарование повышение опыта - <code>CPAutoMelting:int</code>
<br><br>
Увеличит количество опыта, подбираемого игроком. Общий коэффициент рассчитывается, как среднее значение уровня зачарования повышения опыта с брони и инструмента в главной и второй руке. 
<br><br>Система повышения: 1 лвл – даёт 15% шанс удвоить количество опыта. Когда шанс превышает 100%, общий уровень увеличивается в два раза и n% - 100% шанс получит в два раза больше от повышенного
</li>
<br><br>
<li>
Мобы - <code>CPSuperMob:{Type:string,Effects:{Particles:int, Data:[{Id:STRING,Lvl:int}]}}</code>
<br><br>
Позволяет создавать различные яйца призыва не связанных с этими яйцами сущностей, например снеговика. Параметр Type отвечает за тип моба, MobType – необязательный параметр для некоторых мобов, к которым можно применить какие-то дополнительные изменения. Если таковых несколько, указывать стоит через точку. Effects – эффекты для босса
<br><br>
Типы мобов: ArmorStand, Bat, Bee, Blaze, Boat, Cat, CaveSpider, Chicken, Cod, Cow, Creeper, Dolphin, Donkey, Drowned, ElderGuardian, EnderDragon, Enderman, Endermite, Evoker, EvokerFangs, ExperienceOrb, Fireball, Fox, Ghast, Giant, Hoglin, Horse, Husk, Illager, Illusioner, IronGolem, ItemFrame, LargeFireball, Llama, LlamaSpit, MagmaCube, Minecart, Mule, MushroomCow, Ocelot, Painting, Panda, Parrot, Phantom, Pig, Piglin, PiglinBrute, PigZombie, Pillager, PolarBear, PufferFish, Rabbit, Ravager, Salmon, Sheep, Shulker, ShulkerBullet, Silverfish, SizedFireball, Skeleton, SkeletonHorse, Slime, SmallFireball, Snowball, Snowman, Spider, Squid, Stray, Strider, TNTPrimed, TraderLlama, TropicalFish, Turtle, Vex, Villager, Vindicator, WanderingTrader, Witch, WitherSkeleton, WitherSkull, Wolf, Zoglin, Zombie, ZombieHorse, ZombieVillager
<br><br>
Мобы с дополнительным параметром: Кот: TABBY, BLACK, RED, SIAMESE, BRITISH_SHORTHAIR, CALICO, PERSIAN, RAGDOLL, WHITE, JELLIE, ALL_BLACK<br><br>
Эндер дракон: CIRCLING, STRAFING, FLY_TO_PORTAL, LAND_ON_PORTAL, LEAVE_PORTAL, BREATH_ATTACK, SEARCH_FOR_BREATH_ATTACK_TARGET, ROAR_BEFORE_ATTACK, CHARGE_PLAYER, DYING, HOVER<br><br>
Лиса: RED, SNOW<br><br>
Лошадь: цвет:  WHITE, CREAMY, CHESTNUT, BROWN, BLACK, GRAY, DARK_BROWN; стиль: NONE, WHITE, WHITEFIELD, WHITE_DOTS, BLACK_DOTS<br><br>
Лама: CREAMY, WHITE, BROWN, GRAY<br><br>
Грибная корова: RED, BROWN<br><br>
Панда: NORMAL, LAZY, WORRIED, PLAYFUL, BROWN, WEAK, AGGRESSIVE<br><br>
Попугай: RED, BLUE, GREEN, CYAN, GRAY<br><br>
Кролик: BROWN, WHITE, BLACK, BLACK_AND_WHITE, GOLD, SALT_AND_PEPPER, THE_KILLER_BUNNY<br><br>
Тропическая рыба: KOB, SUNSTREAK, SNOOPER, DASHER, BRINELY, SPOTTY, FLOPPER, STRIPEY, GLITTER, BLOCKFISH, BETTY, CLAYFISH<br><br>
Жители: професия: NONE, ARMORER, BUTCHER, CARTOGRAPHER, CLERIC, FARMER, FISHERMAN, FLETCHER, LEATHERWORKER, LIBRARIAN, MASON, NITWIT, SHEPHERD, TOOLSMITH, WEAPONSMITH; тип: DESERT, JUNGLE, PLAINS, SAVANNA, SNOW, SWAMP, TAIGA<br><br>
</li>
</ul>
</div>