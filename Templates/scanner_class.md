## 標準入力（Scannerクラス）

## 基本的なメソッド
メソッド		    用途
next  			  区切り文字までの入力を取得する(デフォルトの区切り文字は空白)
nextLine		  改行までの入力を取得する
nextInt 		  整数の入力を取得する
nextDouble	  小数点を含む数値の入力を取得する
nextBoolean	  真偽値の入力を取得する
useDelimiter	区切り文字を指定する
hasNext		    入力の読み込みが続くか判定する

## サンプルコード
① 指定範囲内の入力値を取得（" ", 改行）
```
package text.section_023;
import java.util.Scanner;
public class Scanner_Test1 {
	public static void main(String[] args) {
		System.out.println("入力しましょう");
	
		// Scannerクラスのオブジェクトを生成する
		Scanner scanner = new Scanner(System.in);
		
		// 入力した内容（ブランクまで）を取得する
		String input  = scanner.next();     // ブランクまでの入力値を出力
    String input2 = scanner.nextLint(); // 改行までの入力値を出力
		
		// 入力した内容を出力する
		System.out.println(input);
    System.out.println(input2);
		
		// Scannerクラスのオブジェクトをクローズする
		scanner.close();
	}
}
```

② 入力値を取得（数値、小数点、真偽値）
入力した内容（int型）を取得する
```
  int input3 = scanner.nextInt();
```
入力した内容（double型）を取得する
```
  double input4 = scanner.nextDouble();
```
入力した内容（boolean型）を取得する
```
  boolean input5 = scanner.nextBoolean();
```

③ 区切記号を指定する（初期は、" "）
```
// 区切記号を変更する
scanner.useDelimiter(",");	// ※ "、(句読点)"ではない
		
// 入力した内容（カンマまで）を取得する
String input = scanner.next(); // 取得は.next()メソッド
```

④ テキストファイルを読込んで、内容を取得する
```
package text.section_023;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Scanner_Test7 {
	public static void main(String[] args) throws FileNotFoundException {
		
		// Scannerクラスのオブジェクトを生成する
		Scanner scanner = new Scanner(new File("src/text/section_023/faile.txt"));
		
		// ファイルの読み込みが続く場合、処理を続ける
		while(scanner.hasNext()) {
			// 改行までの入力を受け取る
			String input = scanner.nextLine();
			System.out.println(input);
		}
	}
}
```