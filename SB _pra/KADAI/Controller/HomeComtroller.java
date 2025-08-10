@Controller
public class HomeController {
  
  @GetMapping("/") // HTTP GETリクエストでルートパス「/」にアクセスされたときの処理
  public String index() {
      return "index"; // templates/index.htmlを返し、トップページを表示
  }

}

// 役割：
  // MVCの「Controller」層として、ルートURLのGETリクエストを受けてView（index.html）を返す。