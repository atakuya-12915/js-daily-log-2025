#　サンプルコード
1. Shopクラス（オーバーロード）
```
package practice.cls;
public class Shop {

	// フィールド
  String name;
  int sales;

  // コンストラクター
  public Shop(String name, int sales){
    this.name = name;
    this.sales = sales;
  }

  // コンストラクターのオーバーロード
  public Shop(String name){
    this ("出店予定", 0);   // this ( , );
  }

  // メソッド
  public void display(){
    System.out.println(this.name +  "：売上高 " + this.sales + "円");
  }
}
```
mainメソッド
```
package practice.cls;
public class MainShop2 {
	public static void main(String[] args) {

  // obj 初期化
  Shop s1 = new Shop("A町店", 150000);
  Shop s2 = new Shop("B公園前店", 180000);
  Shop s3 = new Shop();   // コンストラクタ②適用（nameフィールドのみ引数代入）

  // メソッド呼び出し
  s1.display();
  s2.display();
  s3.display();
  }
}
```

2. Studentクラス（配列）
```
package practice.cls;
public class Student2 {

  // フィールド
  String name;
  int jp;   // 国語
  int en;   // 英語
  int math; // 数学

  // コンスtラクター①
  public Student(String name, int jp, int en, int math){
    this.name = name;
    this.jp = jp;
    this.en = en;
    this.math = math;

  // コンストラクター②：nameフィールドのみ引数代入
  public Student(String name){
    this (name, 0, 0, 0);   // コンストラクター①の設定内容を直接上書き
  }

  // メソッド
  public void display(){
    System.out.println("名前：" + this.name + " 国語：" + jp + "点,"+ "英語：" + en + "点,"+ "数学：" + math + "点,")
  }
}
}
```
mainメソッド
```
package practice.cls;
public class MainStudent2 {
	public static void main(String[] args) {

    // [students配列] を作成（フィールドを要素にもった配列）
    Student[] students = new Student[3];

    // students 配列（フィールド）に要素を代入
    students[0] = new Student("タロウ", 80, 75, 90);
    students[1] = new Student("ハナコ", 90, 70, 80);

    // ジロウ のみ未受験（全教科0点をオートセットするコンストラクタ②を適用）
    students[2] = new Student("ジロウ");  // nameをキーに適用

     // 出力
     System.out.println("成績表");
     for (int i = 0; i < students.length; i++){
      students[i].show();
     }
  }
}   
```

3. コンストラクタなしVer
① objの生成
② フィールド代入
③ 出力