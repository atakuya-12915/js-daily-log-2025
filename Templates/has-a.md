## has-aの関係
  部品（Aクラス）を組み合わせて、1つの製品（SetAクラス）を作る

ハンバーガーメニューの例
・Menuクラス（商品）     ：バーガー名　と　価格　を定義
・SetMenuクラス（セット商品）：セットメニュー（セット名、バーガー名、ドリンク名）を定義
・Mainクラス（実際に作る）   ：セット名、バーガー、ドリンクの３つの引数をセットして、
                        セットメニューを生成する。各クラスのメソッドを実行して出力。

# コード
- Menuクラス
```
package practice.has_a;
public class Menu {

  // フィールド（商品定義）
  String name;    // バーガー名（種類の定義）
  int price;      // バーガーの単価

  // Menu：コンストラクタ
  public Menu(String name, int price){
    this.name = name;
    this.price = price;
  }

  // Menu：displayメソッド（単品価格を表示）
  public void display(){
    System.out.println(this.name + ":" + price + "円");
  }
}
```

- SetMenuクラス
```
package practice.has_a;
public class SetMenu {

  // フィールド（セットメニューの定義）
  String name;  // セットメニューの名前
  Menu burger;  // セット用のバーガー名
  Menu drink;   // セット用のドリンク名

  // SetMenu：コンストラクター
  public SetMenu(String name, Menu burger, Menu drink){
    this.name = name;
    this.burger = burger;
    this.drink = drink;
  }

  // SetMenu：displayメソッド
  public void display(){
    System.out.println(this.name);    // セットメニューの名前
    System.out.println("---");
    // インスタンスメソッドの呼び出し
    burger.display();
    drink.display();
    System.out.println("---");

    // セット価格price を計算して出力　※20%引のセット価格
    int price = (int)((burger.price + drink.price)*0.08);
    System.out.println("セット価格：" + price + "円");
  }
}
```

- Mainクラス
```
package practice.has_a;
public class MainMenu {
	public static void main(String[] args) {

    // Menuクラス：obj 初期化（Menu：コンストラクタ使用）
    // ハンバーガー🍔 を生成
    Menu burger = new Menu("ハンバーガー",150);
    // オレンジジュース🥤 を生成
    Menu drink  = new Menu("オレンジジュース",100);

    // SetMenuクラス：obj 初期化（SetMenu：コンストラクタ使用）
    // セットメニュー🍔🥤 を生成　（obj変数：burger,drink を引数に代入）
    SetMenu burgerSet = SetMenu("ハンバーガーセット",burger,drink);

    // burgerSet：displayメソッド呼び出し（セットメニューを表示）
    burgerSet.display();  // SetMenu：displayメソッド
  }
}
```

- 出力結果
```
セット商品名：ハンバーガーセット
---
ハンバーガー：150円
オレンジジュース：100円
---
セット価格200円
```