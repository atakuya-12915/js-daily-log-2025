## Java クラス・カプセル化
例）Personクラス（nameフィールド）
・nameフィールドを private にすると、そのクラス外からは触れない（アクセス不可）
・getName() などのメソッドを public でクラス内に作成すれば、
  外部からアクセスできる窓口を作れる
・main や他クラスから インスタンス.getName() でnameにアクセスして値を取得できる

（まとめ）カプセル化とは・・・
  ✅ フィールドをカプセル化して、外部から直接触れないように守る
  ✅ 必要なときだけ、getter（やsetter）を通して安全にアクセスできるようにする

|   用語        | 意味                                        |
| ------------ | ------------------------------------------- |
| カプセル化    | データを外部から守るしくみ                       |
| アクセス修飾子 | アクセス範囲を指定するキーワード（例：`private`）  |
| ゲッター      | データを取り出すメソッド                        |
| セッター      | データを設定するメソッド（必要ならチェックを入れる） |


## Cording
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


public class Main {
    public static void main(String[] args) {
        Person p = new Person("たろう", 25);

        // 外部から値を取得・設定できる
        System.out.println(p.getName()); // たろう
        p.setAge(-10);                   // 年齢は0以上にしてください。
        System.out.println(p.getAge());  // 25（変更されていない）
    }
}
