## Java メソッドの基本演習
  Ensyu_1クラスに整数a,bからa+bを求めるaddメソッドを作成してmainで呼び出す

## Cording
```
package ensyu;
public class Ensyu_1 {                      * 作成するクラス名の頭文字は大文字
  public static void main(String[] args) {  * main
    
    // 初期値
    int a = 5;  // 整数a
    int b = 10; // 整数b

    // addメソッドの呼び出し
    int add = add(a,b);                     * addメソッドを変数化
    System.out.println(add);                * System.out.println(add); はコンパイルエラー
                                            * （）内は変数や文字列、整数でないとCPが理解できない
  }                                         * mainここまで

  // addメソッド（戻り値あり）の宣言
  public static int add ( int a, int b ) {
                                            * アクセス修飾子
                                              public = どこからでもアクセス可能
                                              private = クラス内のみアクセス可能
                                            * 戻り値の型 + メソッド名(型 + 引数,,,)
    return a + b;                           * 戻り値あり＝return をセットで記述
  }
}

まとめ（メソッドの宣言方法）
・宣言は通常クラス（main外）で行う
・宣言の際は、アクセス修飾子を指定すること
・もし、メソッドの戻り値を使いたい場合は、voidではなく「データ型を」指定する（上記ではint）
・引数も同様にデータ型を指定する
・メソッドの呼び出しはmainで行う
・メソッドは変数（関数）とは異なり、そのままメソッド名を（）に入れても出力できない
  →定数化してから「定数」として出力する