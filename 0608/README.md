⚙️言語：Java

🕐学習時間：180分

📝学習内容
・メソッド（引数・戻り値・オーバーロード）

🎯本日の目標
・Javaのメソッドを理解する

💡習得したこと
①Javaでは、実行は必ずmain()メソッドからスタートする（main()から呼ばれないと動かない）。関数宣言はクラスに書く。
  →main()の中で関数を呼び出すから先に記述する。その後、クラス内で関数を宣言する。（JavaScriptの書いた順番に上から実行の記法と異なる点）
②final をつけることで変数や引数を書き換えられないようにできる。（final int postageのように）
③メソッドには複数の引数を渡すことができる。メソッドに渡したデータ（引数）に応じて計算結果が変化する。
	（購入金額や送料などの計算をするときに便利）
④Javaでは同じメソッド名でも引数の「型 / 個数 / 順番」が異なるなら別物として扱われる。（int型とString型など）
  ・引数が異なる複数の同名メソッドを定義することを「オーバーロード」と呼ぶ。
  ・オーバーロードの例（例：printlnメソッド）：
    public static void main (String[] args) {
      test(123);        // test(1)を呼び出す
      test(1.23F);      // test(2)を呼び出す
      test(123, 1.23F); // test(3)を呼び出す
      test(1.23F, 123); // test(4)を呼び出す
    }

    // メソッドで引数の「型」を指定　→　関数の引数の「型」に応じて出力結果が変化する
    public static void test (int i) { // 引数の型が「int型」のとき
      System.out.println(i);  // 出力結果：123
    }
    public static void test (float f) { // 引数の型が「float型」のとき
      System.out.println(i);  // 出力結果：1.23F
    }
    public static void test (int i, float f) { // 「int型」→「float型」の順番のとき
      System.out.println(i);  // 出力結果：123, 1.23F
    }
    public static void test (float f, int i) { // 「float型」→「int型」の順番のとき
      System.out.println(i);  // 出力結果：1.23F, 123
    }

❓難しかったこと・疑問
Q. JavaとJavaScriptの記法の違いについて。Javaでは「関数の呼び出し」を最初（main()内）に書くのはなぜ？
A. Javaは「構造の都合で、先にmainで呼び出して、後から定義を書く」必要があるから。
   一方、JavaScriptは「柔軟なので、先に定義して、後から呼び出す」という処理だから。


＊次回やること
Java：クラス、カプセル化