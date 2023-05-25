package com.ahmrh.pierresproduct.model

object FakeProductDataSource {
    val dummyProducts = listOf(
        Product(
            1,
            "Pumpkin Seeds",
            "https://stardewvalleywiki.com/mediawiki/images/9/99/Pumpkin_Seeds.png",
            100,
            "Plant these in the fall. Takes 13 days to mature. Pumpkin can become a Giant Crop.",
            Crop(
                "Pumpkin",
                "https://stardewvalleywiki.com/mediawiki/images/6/64/Pumpkin.png",
                "Fall",
                "13 days",
                320,
                "A fall favorite, grown for its crunchy seeds and delicately flavored flesh. As a bonus, the hollow shell can be carved into a festive decoration."
            )
        ),

        Product(
            2,
            "Eggplant Seeds",
            "https://stardewvalleywiki.com/mediawiki/images/f/f9/Eggplant_Seeds.png",
            20,
            "Plant these in the fall. Takes 5 days to mature, and continues to produce after first harvest. When harvested, each Eggplant bush gives 1 Eggplant every 5 days, with a 0.2% chance for more Eggplants.",

            Crop(
                "Eggplant",
                "https://stardewvalleywiki.com/mediawiki/images/8/8f/Eggplant.png",
                "Fall",
                "5 days",
                60,
                "A rich and wholesome relative of the tomato. Delicious fried or stewed."
            )
        ),

        Product(
            3,
            "Corn Seeds",
            "https://stardewvalleywiki.com/mediawiki/images/d/d1/Corn_Seeds.png",
            150,
            "Plant these in the summer or fall. Takes 14 days to mature, and continues to produce after first harvest.",

            Crop(
                "Corn",
                "https://stardewvalleywiki.com/mediawiki/images/f/f8/Corn.png",
                "Summer",
                "14 days",
                50,
                "One of the most popular grains. The sweet, fresh cobs are a summer favorite."
            )
        ),
        Product(
            4,
            "Cranberry Seeds",
            "https://stardewvalleywiki.com/mediawiki/images/e/ec/Cranberry_Seeds.png",
            240,
            "Plant these in the fall. Takes 7 days to mature, and continues to produce after first harvest. When harvested, each Cranberry plant gives 2 Cranberries every 5 days with a 10% chance of one or more extra cranberries",

            Crop(
                "Cranberries",
                "https://stardewvalleywiki.com/mediawiki/images/6/6e/Cranberries.png",
                "Fall",
                "7 days",
                75,
                "These tart red berries are a traditional winter food."
            )
        ),

        Product(
            5,
            "Melon Seeds",
            "https://stardewvalleywiki.com/mediawiki/images/5/5e/Melon_Seeds.png",
            80,
            "Plant these in the summer. Takes 12 days to mature. Melons can become a Giant Crop.",

            Crop(
                "Melon",
                "https://stardewvalleywiki.com/mediawiki/images/1/19/Melon.png",
                "Summer",
                "12 days",
                250,
                "A cool, sweet summer treat."
            )
        ),
        Product(
            6,
            "Blueberry Seeds",
            "https://stardewvalleywiki.com/mediawiki/images/8/81/Blueberry_Seeds.png",
            80,
            "Plant these in the summer. Takes 13 days to mature, and continues to produce after first harvest. Each harvest yields 3 Blueberries, with a 2% chance of more blueberries.",

            Crop(
                "Blueberry",
                "https://stardewvalleywiki.com/mediawiki/images/9/9e/Blueberry.png",
                "Summer",
                "13 days",
                50,
                "A popular berry reported to have many health benefits. The blue skin has the highest nutrient concentration."
            )
        ),
        Product(
            7,
            "Parsnip Seeds",
            "https://stardewvalleywiki.com/mediawiki/images/d/d3/Parsnip_Seeds.png",
            20,
            "Plant these in the spring. Takes 4 days to mature.",

            Crop(
                "Parsnip",
                "https://stardewvalleywiki.com/mediawiki/images/d/db/Parsnip.png",
                "Spring",
                "4 days",
                35,
                "A spring tuber closely related to the carrot. It has an earthy taste and is full of nutrients."
            )
        ),
        Product(
            8,
            "Potato Seeds",
            "https://stardewvalleywiki.com/mediawiki/images/4/44/Potato_Seeds.png",
            50,
            "Plant these in the spring. Takes 6 days to mature, and has a chance of yielding multiple potatoes at harvest.",

            Crop(
                "Potato",
                "https://stardewvalleywiki.com/mediawiki/images/c/c2/Potato.png",
                "Spring",
                "6 days",
                80,
                "A widely cultivated tuber."
            )
        ),
        Product(
            9,
            "Kale Seeds",
            "https://stardewvalleywiki.com/mediawiki/images/0/00/Kale_Seeds.png",
            70,
            "Plant these in the spring. Takes 6 days to mature. Harvest with the scythe.",

            Crop(
                "Kale",
                "https://stardewvalleywiki.com/mediawiki/images/d/d1/Kale.png",
                "Spring",
                "6 days",
                110,
                "The waxy leaves are great in soups and stir frys."
            )
        ),
        Product(
            10,
            "Garlic Seeds",
            "https://stardewvalleywiki.com/mediawiki/images/d/d5/Garlic_Seeds.png",
            40,
            "Plant these in the spring. Takes 4 days to mature.",

            Crop(
                "Garlic",
                "https://stardewvalleywiki.com/mediawiki/images/c/cc/Garlic.png",
                "Spring",
                "4 days",
                60,
                "Adds a wonderful zestiness to dishes. High quality garlic can be pretty spicy."
            )
        ),
//        custom
//        Product(
//            0,
//            "Qi Bean",
//            "https://stardewvalleywiki.com/mediawiki/images/a/a7/Qi_Bean.png",
//            0,
//            "Plant these in any season. Water every day to produce a Qi Fruit. Takes 4 days to mature.",
//
//            Crop(
//                "Qi Fruit",
//                "https://stardewvalleywiki.com/mediawiki/images/b/b9/Qi_Fruit.png",
//                "All Season",
//                "4 days",
//                3,
//                "Mr. Qi has challenged you to ship 500 of these strange melons."
//            )
//        ),
//
//        Product(
//            0,
//            "Qi Bean",
//            "https://stardewvalleywiki.com/mediawiki/images/a/a7/Qi_Bean.png",
//            0,
//            "Plant these in any season. Water every day to produce a Qi Fruit. Takes 4 days to mature.",
//
//            Crop(
//                "Qi Fruit",
//                "https://stardewvalleywiki.com/mediawiki/images/b/b9/Qi_Fruit.png",
//                "All Season",
//                "4 days",
//                3,
//                "Mr. Qi has challenged you to ship 500 of these strange melons."
//            )
//        ),
//
//        Product(
//            0,
//            "Qi Bean",
//            "https://stardewvalleywiki.com/mediawiki/images/a/a7/Qi_Bean.png",
//            0,
//            "Plant these in any season. Water every day to produce a Qi Fruit. Takes 4 days to mature.",
//
//            Crop(
//                "Qi Fruit",
//                "https://stardewvalleywiki.com/mediawiki/images/b/b9/Qi_Fruit.png",
//                "All Season",
//                "4 days",
//                3,
//                "Mr. Qi has challenged you to ship 500 of these strange melons."
//            )
//        ),
//
//        Product(
//            0,
//            "Qi Bean",
//            "https://stardewvalleywiki.com/mediawiki/images/a/a7/Qi_Bean.png",
//            0,
//            "Plant these in any season. Water every day to produce a Qi Fruit. Takes 4 days to mature.",
//
//            Crop(
//                "Qi Fruit",
//                "https://stardewvalleywiki.com/mediawiki/images/b/b9/Qi_Fruit.png",
//                "All Season",
//                "4 days",
//                3,
//                "Mr. Qi has challenged you to ship 500 of these strange melons."
//            )
//        ),
//
//        Product(
//            0,
//            "Qi Bean",
//            "https://stardewvalleywiki.com/mediawiki/images/a/a7/Qi_Bean.png",
//            0,
//            "Plant these in any season. Water every day to produce a Qi Fruit. Takes 4 days to mature.",
//
//            Crop(
//                "Qi Fruit",
//                "https://stardewvalleywiki.com/mediawiki/images/b/b9/Qi_Fruit.png",
//                "All Season",
//                "4 days",
//                3,
//                "Mr. Qi has challenged you to ship 500 of these strange melons."
//            )
//        ),
//
//        Product(
//            0,
//            "Qi Bean",
//            "https://stardewvalleywiki.com/mediawiki/images/a/a7/Qi_Bean.png",
//            0,
//            "Plant these in any season. Water every day to produce a Qi Fruit. Takes 4 days to mature.",
//
//            Crop(
//                "Qi Fruit",
//                "https://stardewvalleywiki.com/mediawiki/images/b/b9/Qi_Fruit.png",
//                "All Season",
//                "4 days",
//                3,
//                "Mr. Qi has challenged you to ship 500 of these strange melons."
//            )
//        ),
    )
}