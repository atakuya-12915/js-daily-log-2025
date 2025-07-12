# DIとは？
　直訳：依存性の注入　→　「オブジェクト」の注入　と考える！
  他のクラスのobjを「自分で作らずに」他から「受け取る」こと

# DIコンテナとは？
　→DIを注入する「オブジェクト工場🏭」（事前にobjを作って必要な場所に注入する）
  ・Springアプリを起動すると自動でDIコンテナを立ち上げる
  ・必要なオブジェクト（クラス）を事前に作って必要なところに渡す（＝注入）

# よく使うアノテーションの種類と役割
| アノテーション                        | 説明                                      |
| ------------------------------ | --------------------------------------- |
| `@Component`                   | 自作クラスをSpringに「登録（=管理対象にする）」する           |
| `@Service`                     | サービス層に使う。中身は`@Component`と同じ（意味付け）       |
| `@Repository`                  | DBアクセス層に使う。例外処理などが自動化される                |
| `@Controller`                  | Webリクエストを受け取るコントローラー                    |
| `@RestController`              | `@Controller + @ResponseBody`（JSON返却向き） |
| `@Autowired`                   | Springが自動でオブジェクトを注入してくれる                |
| `@Bean`                        | メソッドで生成したオブジェクトを、Springに登録したいとき         |
| `@Configuration`               | `@Bean`を書くクラスに付ける（設定クラスの印）              |
| `@RequestMapping`              | URLに対応した処理を指定する（GET/POSTなど）             |
| `@GetMapping` / `@PostMapping` | 簡易版。URL+HTTPメソッドに対応                     |

# @Beanの使い方
```
@Configuration
public class AppConfig {

    @Bean
    public HelloService helloService() {
        return new HelloService();  // このインスタンスがDIコンテナに登録される
    }
}
```