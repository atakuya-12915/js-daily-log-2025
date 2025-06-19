## この学習ログの目的
* ①振り返り用：1週間ごとにまとめて読んで自身の成長を可視化
* ②言語化：どんな力を意識して伸ばしたかを言語化
* ③モチベーション維持：進捗を可視化することで学習を継続しやすくする

## 今日の学習内容
⚙️言語 Java

🕐学習時間
📅 日付　2025/06/20
開始時刻　0:00, 6:00
終了時刻　1:30, 11:00
合計学習時間(h)　7.5

🎯本日の学習目標
・ラムダ式について学ぶ #２

📝学習内容
・ラムダ式でコードの短縮化を学ぶ #2(StreamSPI)

💡 気づき・理解できたこと
・StreamAPI実装の流れ
  1. ListなどからStreamを生成する
  2. 生成したStreamに対して、絞り込みなどの中間処理を行う
  3. 中間処理を行ったStreamに対して結果の取得などの終端処理を行う

# サンプルコード
  1. 配列要素の絞り込み
  ```
  package text.section_027;

  import java.util.ArrayList;
  import java.util.Arrays;
  import java.util.List;

  public class LambdaExec_Test4 {
    public static void main(String[] args) {
      // 配列の生成
      List<String> list = new ArrayList<>(Arrays.asList("ab","ac","bc","cd","de"));

      list.stream() // Streamの生成
          .filter(param -> param.startsWith("a")) // 絞り込みの中間処理を行う
          .forEach(System.out::println);  // 結果取得の終端処理を行う
  }
  ```

  2. 配列要素の並び替え
  ```
  package text.section_27;

  import java.util.ArrayList;
  import java.util.Arrays;
  import java.util.List;

  public class LambdaExec_Test4 {
    public static void main(String[] args) {
      // 配列の生成
      List<String> list = new ArrayList<>(Arrays.asList("a","e","d","b","c"));

      list.stream() // Streamの生成
          .sorted() // 並び替えの中間処理を行う
          .forEach(System.out::println);  // 結果取得の終端処理を行う
    }
  }
  ```

❓難しかったこと・疑問
Q. 
A. 

📤 アウトプットしたこと
・GitHubに学習ログpush（#今日の積み上げ）

🌱 明日の目標
・教材学習（DB）を進める

📈 学習の満足度（1〜5）
☐ 1 ☐ 2 ◽️ 3 ☐ 4 ☐ 5
（理由：ラムダ式を使いこなせれば、コードをかなり簡素化短縮が可能と学んだ）

