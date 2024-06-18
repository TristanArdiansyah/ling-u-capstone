package com.bangkit.capstone.lingu.data.database.helper

import com.bangkit.capstone.lingu.R
import com.bangkit.capstone.lingu.data.database.Course
import com.bangkit.capstone.lingu.data.database.Characters

object InitialDataSource {
    fun getCourse(): List<Course> {
        return listOf(
            Course(1, "Location", R.drawable.location_image),
            Course(2, "Body Parts", R.drawable.body_image),
            Course(3, "Arithmetic", R.drawable.arithmetic_image),
            Course(4, "Nature", R.drawable.nature_image),
            Course(5, "Conversational", R.drawable.conversation_image),
        )
    }
    fun getCharacters(): List<Characters> {
        return listOf(
            Characters(1, "上", "shàng", "up, above, on", "書在桌子上。", "Shū zài zhuōzi shàng.", "The book is on the table.", "null", 1, 0),
            Characters(2, "下", "xià", "down, below, under", "貓在桌子下。", "Māo zài zhuōzi xià.", "The cat is under the table.", "null", 1, 1),
            Characters(9, "中", "zhōng", "middle, center", "他站在隊伍中間。", "Tā zhàn zài duìwu zhōngjiān.", "He is standing in the middle of the line.", "null", 1, 2),
            Characters(3, "內", "nèi", "inside, internal", "房間內很溫暖。", "Fángjiān nèi hěn wēnnuǎn.", "It's warm inside the room.", "null", 1, 3),
            Characters(5, "前", "qián", "front, before, ago", "我的前面有一棵樹。", "Wǒ de qiánmiàn yǒu yī kē shù.", "There is a tree in front of me.", "null", 1, 4),
            Characters(14, "北", "běi", "north", "北極熊生活在北極。", "Běijíxióng shēnghuó zài běijí.", "Polar bears live in the North Pole.", "null", 1, 5),
            Characters(13, "南", "nán", "south", "我們學校在城市的南邊。", "Wǒmen xuéxiào zài chéngshì de nánbian.", "Our school is in the south of the city.", "null", 1, 6),
            Characters(8, "右", "yòu", "right", "請向右轉。", "Qǐng xiàng yòu zhuǎn.", "Please turn right.", "null", 1, 7),
            Characters(22, "國", "guó", "country, nation", "我愛我的國家。", "Wǒ ài wǒ de guójiā.", "I love my country.", "null", 1, 8),
            Characters(4, "外", "wài", "outside, external", "門外有人在敲門。", "Mén wài yǒu rén zài qiāo mén.", "Someone is knocking at the door from outside.", "null", 1, 9),
            Characters(7, "左", "zuǒ", "left", "請向左轉。", "Qǐng xiàng zuǒ zhuǎn.", "Please turn left.", "null", 1, 10),
            Characters(16, "市", "shì", "city, market", "我住在一個大城市。", "Wǒ zhù zài yīgè dà chéngshì.", "I live in a big city.", "null", 1, 11),
            Characters(18, "店", "diàn", "store, shop", "我要去便利店買東西。", "Wǒ yào qù biànlì diàn mǎi dōngxī.", "I'm going to the convenience store to buy something.", "null", 1, 12),
            Characters(6, "後", "hòu", "back, behind, after", "我的後面有一座山。", "Wǒ de hòumiàn yǒu yī zuò shān.", "There is a mountain behind me.", "null", 1, 13),
            Characters(21, "房", "fáng", "house, room", "我的房子很大。", "Wǒ de fángzi hěn dà.", "My house is big.", "null", 1, 14),
            Characters(11, "東", "dōng", "east", "太陽從東方升起。", "Tàiyáng cóng dōngfāng shēngqǐ.", "The sun rises from the east.", "null", 1, 15),
            Characters(19, "田", "tián", "field", "農夫在田裡工作。", "Nóngfū zài tián lǐ gōngzuò.", "The farmer works in the field.", "null", 1, 16),
            Characters(20, "街", "jiē", "street", "這條街有很多商店。", "Zhè tiáo jiē yǒu hěnduō shāngdiàn.", "There are many shops on this street.", "null", 1, 17),
            Characters(12, "西", "xī", "west", "太陽從西方落下。", "Tàiyáng cóng xīfāng luòxià.", "The sun sets in the west.", "null", 1, 18),
            Characters(15, "里", "lǐ", "neighborhood, village", "我住在這個里已經五年了。", "Wǒ zhù zài zhège lǐ yǐjīng wǔ niánle.", "I have lived in this neighborhood for five years.", "null", 1, 19),
            Characters(17, "門", "mén", "door, gate", "請把門關上。", "Qǐng bǎ mén guān shàng.", "Please close the door.", "null", 1 , 20),
            Characters(10, "間", "jiān", "between, among, room", "我的房間在二樓。", "Wǒ de fángjiān zài èr lóu.", "My room is on the second floor.", "null", 1, 21),

            Characters(23, "手", "shǒu", "hand", "我的手很小。", "Wǒ de shǒu hěn xiǎo.", "My hands are small.", "null", 2, 0),
            Characters(24, "腳", "jiǎo", "foot", "我的腳很痛。", "Wǒ de jiǎo hěn tòng.", "My feet hurt.", "null", 2, 1),
            Characters(25, "頭", "tóu", "head", "我的頭很大。", "Wǒ de tóu hěn dà.", "My head is big.", "null", 2, 2),
            Characters(26, "眼", "yǎn", "eye", "我的眼睛是藍色的。", "Wǒ de yǎnjīng shì lán sè de.", "My eyes are blue.", "null", 2, 3),
            Characters(27, "耳", "ěr", "ear", "我的耳朵很大。", "Wǒ de ěrduo hěn dà.", "My ears are big.", "null", 2, 4),
            Characters(28, "口", "kǒu", "mouth", "我的口很渴。", "Wǒ de kǒu hěn kě.", "My mouth is thirsty.", "null", 2, 5),
            Characters(29, "鼻", "bí", "nose", "我的鼻子很小。", "Wǒ de bízi hěn xiǎo.", "My nose is small.", "null", 2, 6),
            Characters(30, "心", "xīn", "heart", "我的心很累。", "Wǒ de xīn hěn lèi.", "My heart is tired.", "null", 2, 7),
            Characters(31, "面", "miàn", "face", "你的面很漂亮。", "Nǐ de miàn hěn piàoliang.", "Your face is beautiful.", "null", 2, 8),
            Characters(32, "身", "shēn", "body", "我的身體很健康。", "Wǒ de shēntǐ hěn jiànkāng.", "My body is healthy.", "null", 2, 9),
            Characters(33, "血", "xuè", "blood", "我的血型是O型。", "Wǒ de xuèxíng shì O xíng.", "My blood type is O.", "null", 2, 10),
            Characters(34, "牙", "yá", "tooth", "我的牙很白。", "Wǒ de yá hěn bái.", "My teeth are white.", "null", 2, 11),
            Characters(35, "皮", "pí", "skin", "我的皮膚很白。", "Wǒ de pífū hěn bái.", "My skin is white.", "null", 2, 12),
            Characters(36, "髮", "fà", "hair", "我的頭髮很長。", "Wǒ de tóufa hěn cháng.", "My hair is long.", "null", 2, 13),
            Characters(37, "骨", "gǔ", "bone", "我的骨頭很硬。", "Wǒ de gǔtou hěn yìng.", "My bones are hard.", "null", 2, 14),

            Characters(44, "七", "qī", "seven", "彩虹有七種顏色。", "Cǎihóng yǒu qī zhǒng yánsè.", "A rainbow has seven colors.", "null", 3, 0),
            Characters(40, "三", "sān", "three", "我有三個姊妹。", "Wǒ yǒu sān ge zǐmèi.", "I have three sisters.", "null", 3, 1),
            Characters(50, "乘", "chéng", "multiply/times", "三乘五等于十五。", "Sān chéng wǔ děngyú shíwǔ.", "Three times five equals fifteen.", "null", 3, 2),
            Characters(46, "九", "jiǔ", "nine", "貓有九條命。", "Māo yǒu jiǔ tiáo mìng.", "A cat has nine lives.", "null", 3, 3),
            Characters(39, "二", "èr", "two", "一加一等于二。", "Yī jiā yī děngyú èr.", "One plus one equals two.", "null", 3, 4),
            Characters(53, "于", "yú", "equals", "一加一等于二。", "Yī jiā yī děngyú èr.", "One plus one equals two.", "null", 3, 5),
            Characters(42, "五", "wǔ", "five", "我今天工作了五個小時。", "Wǒ jīntiān gōngzuòle wǔ ge xiǎoshí.", "I worked for five hours today.", "null", 3, 6),
            Characters(45, "八", "bā", "eight", "章魚有八隻腳。", "Zhāngyú yǒu bā zhī jiǎo.", "An octopus has eight legs.", "null", 3, 7),
            Characters(43, "六", "liù", "six", "一個星期有六個工作天。", "Yīgè xīngqī yǒu liù ge gōngzuò tiān.", "There are six workdays in a week.", "null", 3, 8),
            Characters(48, "加", "jiā", "plus/add", "二加二等于四。", "Èr jiā èr děngyú sì.", "Two plus two equals four.", "null", 3, 9),
            Characters(47, "十", "shí", "ten", "我今年十歲。", "Wǒ jīnnián shí suì.", "I am ten years old this year.", "null", 3, 10),
            Characters(41, "四", "sì", "four", "這本書有四百頁。", "Zhè běn shū yǒu sìbǎi yè.", "This book has four hundred pages.", "null", 3, 11),
            Characters(49, "減", "jiǎn", "minus/subtract", "五減三等于二。", "Wǔ jiǎn sān děngyú èr.", "Five minus three equals two.", "null", 3, 12),
            Characters(52, "等", "děng", "equals", "一加一等于二。", "Yī jiā yī děngyú èr.", "One plus one equals two.", "null", 3, 13),
            Characters(51, "除", "chú", "divide", "十除二等于五。", "Shí chú èr děngyú wǔ.", "Ten divided by two equals five.", "null", 3, 14),
            Characters(38, "零", "líng", "zero", "我有零個蘋果。", "Wǒ yǒu líng ge píngguǒ.", "I have zero apples.", "null", 3, 15),

            Characters(68, "北", "běi", "north", "北極熊生活在北極。", "Běijíxióng shēnghuó zài běijí.", "Polar bears live in the North Pole.", "null", 4, 0),
            Characters(67, "南", "nán", "south", "我們學校在城市的南邊。", "Wǒmen xuéxiào zài chéngshì de nánbian.", "Our school is in the south of the city.", "null", 4, 1),
            Characters(60, "土", "tǔ", "earth, soil", "農夫在土裡種菜。", "Nóngfū zài tǔ lǐ zhòng cài.", "The farmer plants vegetables in the soil.", "null", 4, 2),
            Characters(55, "地", "dì", "earth, ground", "地震時，地會搖晃。", "Dìzhèn shí, dì huì yáohuàng.", "The ground shakes during an earthquake.", "null", 4, 3),
            Characters(54, "天", "tiān", "sky, heaven", "今天的天很藍。", "Jīntiān de tiān hěn lán.", "The sky is very blue today.", "null", 4, 4),
            Characters(56, "山", "shān", "mountain", "他喜歡爬山。", "Tā xǐhuan pá shān.", "He likes to climb mountains.", "null", 4, 5),
            Characters(61, "日", "rì", "sun", "日出江花红胜火，春来江水绿如蓝。", "Rì chū jiāng huā hóng shèng huǒ, chūn lái jiāng shuǐ lǜ rú lán.", "Sunrise on the river, flowers redder than fire, spring returns, river water green as blue.", "null", 4, 6),
            Characters(62, "月", "yuè", "moon", "今晚的月亮很圓。", "Jīnwǎn de yuèliang hěn yuán.", "The moon is very round tonight.", "null", 4, 7),
            Characters(58, "木", "mù", "wood, tree", "這張桌子是用木頭做的。", "Zhè zhāng zhuōzi shì yòng mùtou zuò de.", "This table is made of wood.", "null", 4, 8),
            Characters(65, "東", "dōng", "east", "太陽從東方升起。", "Tàiyáng cóng dōngfāng shēngqǐ.", "The sun rises from the east.", "null", 4, 9),
            Characters(57, "水", "shuǐ", "water", "我每天喝很多水。", "Wǒ měitiān hē hěnduō shuǐ.", "I drink a lot of water every day.", "null", 4, 10),
            Characters(59, "火", "huǒ", "fire", "小心火燭！", "Xiǎoxīn huǒzhú!", "Be careful of fire!", "null", 4, 11),
            Characters(66, "西", "xī", "west", "太陽從西方落下。", "Tàiyáng cóng xīfāng luòxià.", "The sun sets in the west.", "null", 4, 12),
            Characters(63, "雨", "yǔ", "rain", "昨天下了很大的雨。", "Zuótiān xiàle hěn dà de yǔ.", "It rained heavily yesterday.", "null", 4, 13),
            Characters(64, "雪", "xuě", "snow", "冬天會下雪。", "Dōngtiān huì xià xuě.", "It snows in winter.", "null", 4, 14),

            Characters(69, "我", "wǒ", "I, me", "我是學生。", "Wǒ shì xuésheng.", "I am a student.", "Pronoun", 5, 0),
            Characters(70, "你", "nǐ", "you (informal)", "你叫什麼名字？", "Nǐ jiào shénme míngzi?", "What is your name?", "Pronoun", 5, 1),
            Characters(71, "您", "nín", "you (formal)", "您好，請問您貴姓？", "Nín hǎo, qǐngwèn nín guìxìng?", "Hello, may I ask your surname?", "Pronoun", 5, 2),
            Characters(72, "他", "tā", "he, him", "他是我的朋友。", "Tā shì wǒ de péngyou.", "He is my friend.", "Pronoun", 5, 3),
            Characters(73, "她", "tā", "she, her", "她是我的老師。", "Tā shì wǒ de lǎoshī.", "She is my teacher.", "Pronoun", 5, 4),
            Characters(74, "它", "tā", "they, them (for things/animals)", "它們是我的寵物。", "Tāmen shì wǒ de chǒngwù.", "They are my pets.", "Pronoun", 5, 5),
            Characters(75, "們", "men", "they, them (for things/animals)", "它們是我的寵物。", "Tāmen shì wǒ de chǒngwù.", "They are my pets.", "Pronoun", 5, 6),
            Characters(76, "叫", "jiào", "to be called, to call", "我叫李明。", "Wǒ jiào Lǐ Míng.", "My name is Li Ming.", "Verb", 5, 7),
            Characters(77, "是", "shì", "to be, is, am, are", "她是醫生。", "Tā shì yīshēng.", "She is a doctor.", "Verb", 5, 8),
            Characters(78, "好", "hǎo", "good, well, okay", "今天天氣很好。", "Jīntiān tiānqì hěn hǎo.", "The weather is very good today.", "Adjective/Adverb", 5, 9),
            Characters(79, "住", "zhù", "to live, to reside", "我住在台北。", "Wǒ zhù zài Táiběi.", "I live in Taipei.", "Verb", 5, 10),
            Characters(80, "學", "xué", "to learn, to study", "我在學中文。", "Wǒ zài xué Zhōngwén.", "I am learning Chinese.", "Verb", 5, 11),
            Characters(81, "做", "zuò", "to do, to make", "他在做飯。", "Tā zài zuò fàn.", "He is cooking.", "Verb", 5, 12),
            Characters(82, "不", "bù", "not, no", "我不去學校。", "Wǒ bù qù xuéxiào.", "I am not going to school.", "Adverb", 5, 13),
            Characters(83, "也", "yě", "also, too", "我也想去。", "Wǒ yě xiǎng qù.", "I also want to go.", "Adverb", 5, 14),
            Characters(84, "這", "zhè", "this", "這是我的書。", "Zhè shì wǒ de shū.", "This is my book.", "Pronoun/Adjective", 5, 15),
            Characters(85, "那", "nà", "that", "那是你的車嗎？", "Nà shì nǐ de chē ma?", "Is that your car?", "Pronoun/Adjective", 5, 16),
            Characters(86, "很", "hěn", "very, quite", "這個蛋糕很好吃。", "Zhège dàngāo hěn hǎochī.", "This cake is very delicious.", "Adverb", 5, 17),
            Characters(87, "嗎", "ma", "question particle", "你吃飯了嗎？", "Nǐ chīfànle ma?", "Have you eaten?", "Particle", 5, 18),
            Characters(88, "呢", "ne", "question particle (softer)", "你呢？", "Nǐ ne?", "What about you?", "Particle", 5, 19),
        )
    }
}