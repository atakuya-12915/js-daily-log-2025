## 🌱 1. **カプセル化（Encapsulation）**とは？

**「データ（変数）と、そのデータを操作する方法（メソッド）を1つにまとめ、外部から勝手に変更できないようにする考え方」**

💡イメージ：
・薬の「カプセル」を思い浮かべる。
・薬の成分（データ）をそのままバラバラにしてしまうと危険。
  →カプセルに包むことで、安全に使えるようになります。

## 🔒 2. アクセス修飾子とは？
クラスの変数やメソッドに「誰からアクセスできるか」を指定するキーワード。
主に使われるのは3つ(+デフォルト)：

public   ：どこからでもアクセス可能（外部にも公開）
private  ：クラスの中だけでアクセス可能（外部から隠す）
protected：クラス自身と、その子クラスからアクセス可能

## 📦 3. なぜカプセル化が必要な理由
①：勝手に変えられないようにする（安全性）
    → 例えば、「年齢」がマイナスになってしまうなど、不正な値の代入を防ぐ

②：変更の影響を小さくする（保守性）
    → クラスの中の構造を外に知られずに済むため、中身を自由に改善できる

## 📬 4. ゲッターとセッターとは？
カプセル化で [private] にした [変数] に間接的にアクセスするための[メソッド]です。

[ゲッター（getter）]：値を取り出す
    → get〇〇()という名前にするのが一般的

[セッター（setter）]：値を設定する
    → set〇〇(新しい値)の形にする

## 🧑‍💻 5. サンプルコード
```
public class Person {
    // カプセル化：外部から直接アクセスできないようにする
    private String name;
    private int age;

    // コンストラクタ（初期設定）
    public Person(String name, int age) {
        this.name = name;
        setAge(age); // セッターを通して代入（バリデーション付き）
    }

    // ゲッター（nameを取得）
    public String getName() {
        return name;
    }

    // セッター（nameを設定）
    public void setName(String name) {
        this.name = name;
    }

    // ゲッター（ageを取得）
    public int getAge() {
        return age;
    }

    // セッター（ageを設定）→ マイナスは禁止！
    public void setAge(int age) {
        if (age >= 0) {
            this.age = age;
        } else {
            System.out.println("年齢は0以上にしてください。");
        }
    }
}
```

## ✔️ 利用例：
```
public class Main {
    public static void main(String[] args) {
        Person p = new Person("たろう", 25);

        // 外部から値を取得・設定できる
        System.out.println(p.getName()); // たろう
        p.setAge(-10);                   // 年齢は0以上にしてください。
        System.out.println(p.getAge());  // 25（変更されていない）
    }
}
```

## 🎯 まとめ
カプセル化　　：データを外部から守るしくみ
アクセス修飾子：アクセス範囲を指定するキーワード（例：private）
ゲッター　　　：データを取り出すメソッド
セッター　　　：データを設定するメソッド（必要ならチェックを入れる）