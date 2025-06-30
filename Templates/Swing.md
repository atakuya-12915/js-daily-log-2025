## 簡単なポップアップウインドウ アプリの実装
# 作成手順
1. ライブラリを使う：1行目にSwingをインポートする
    import javax.swing.*;

  * 以下、main()メソッドの内容

2. フレームの作成
  ・JFrameクラスを使う
    アプリのフレーム(メイン画面)を作成し基本的な設定を行う

  ・ボタンの作成
    JButtonクラスを使ってクリックできるボタン機能をフレームに追加する

3. ポップアップ画面の作成
    addActionListener()メソッドを使用して、ボタンクリック時の動作を設定する。
    ボタンがクリックされるタイミングでJOptionPane.showMessageDialog()メソッドが実行され、ポップアップ画面を表示する。

4. フレームの表示
    アプリの画面を表示する。

# サンプルコード
```
package swing;
// Swingのパッケージをインポート
import javax.swing.*;   // フレーム設定やボタン、画面の作成が完了すると下記3つに自動で上書きされる
// import javax.swing.JButton;
// import javax.swing.JFrame;
// import javax.swing.JOptionPane;

public class Hello {
  public static void main(String[] args){

    // 1.フレームの設定
    JFrame frame = new JFrame("Swing サンプルアプリ);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300,200);

    // 2.ボタンの作成
    JButton button = new JButton("こんにちは！クリックしてください);
    button.setBounds(50,50,200,30);
    frame.add(button);

    // 3.ポップアップ画面の作成
    button.addActionListener(e -> {
      JOptionPane.showMessageDialog(frame, "Hello World!");
    });

    // 4.フレームの表示
    frame.setVisible(true);
  }
}
```
