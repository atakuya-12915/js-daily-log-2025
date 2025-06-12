## Java　classのテンプレート
1. パッケージ宣言
package [packageName];

2. クラス宣言
public class [ClassName)] {   ←キャメルケース（先頭大文字）
    フィールドの宣言と初期化
}

3. フィールドの初期化（例：初期値指定）
private int number = 0;
private String name = "taro";
　※コンストラクタ内で初期化してもOK

4. コンストラクタ
① 引数なしの場合
public ClassName() {
    初期化処理[3.の内容を記載];
}

② 引数ありの場合(例)
public ClassName(int number, String name) {
    this.number = number;
    this.name = name;
}
5. メソッドの定義
① 戻り値なし（= void）
  引数なしメソッドの場合
  public void doSomething() {
      System.out.println("処理を実行");
  }

  引数ありメソッドの場合
  public void setCount(int number) {
      this.number = number;
  }

② 戻り値あり（= [戻り値]の型指定 & return [戻り値]をセット）
  引数なしメソッドの場合
  public int getCount() {
      return number;   ←return の後は()など不要
        ↑number (= 戻り値)の型を指定しないと、returnした値を使用できない
  }

  引数ありメソッドの場合
  public String greet(String text) {
      return text + " " + name;
  }

6. mainメソッド（クラス内 or 別クラス）
public static void main(String[] args) {

    // インスタンス化
    ClassName obj = new ClassName();
               ↑ [作成したいしたいオブジェクト名]

    // メソッド呼び出し（引数なし）
    obj.doSomething();
     ↑ 作成した[インスタンス名].メソッド名()

    // メソッド呼び出し（引数あり:[()内]に[代入値]を記述）
    obj.setCount(10);
    String greeting = obj.greet("Hello");
    System.out.println(greeting);
}

7. インスタンス化の例
  ⭕️ 通常：引数あり
  ClassName obj2 = new ClassName(5, "Taro");

  　                              ↑ この引数をメソッドに渡す
  ◽️ 引数なし
  ClassName obj1 = new ClassName();

8. メソッドの呼び出し例
  ⭕️ 通常：引数ありメソッド
  obj2.setCount(100);
  int count = obj2.getCount();
  String msg = obj2.greet("Hi");

  ◽️ 引数なしメソッド
  obj1.doSomething();


## サンプルコード
```
package your.package.name;

public class ClassName {
    // フィールド初期化
    private int count = 0;
    private String name = "default";

    // コンストラクタ
    public ClassName() {
        // 初期化処理
    }

    public ClassName(int count, String name) {
        this.count = count;
        this.name = name;
    }

    // 戻り値なしメソッド（引数なし）
    public void doSomething() {
        System.out.println("処理を実行");
    }

    // 戻り値なしメソッド（引数あり）
    public void setCount(int count) {
        this.count = count;
    }

    // 戻り値ありメソッド（引数なし）
    public int getCount() {
        return count;
    }

    // 戻り値ありメソッド（引数あり）
    public String greet(String prefix) {
        return prefix + " " + name;
    }

    // mainメソッド（別クラスにする場合も多い）
    public static void main(String[] args) {
        ClassName obj1 = new ClassName();
        obj1.doSomething();

        ClassName obj2 = new ClassName(5, "Taro");
        obj2.setCount(100);
        System.out.println(obj2.getCount());
        System.out.println(obj2.greet("Hello"));
    }
}
