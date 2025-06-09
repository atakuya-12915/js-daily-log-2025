// 商品クラスを定義
class Product {
  // コンストラクタ（初期化）
  constructor( name, price, stock ) {
    this.name = name;
    this.price = price;
    this.stock = stock;
  }

  /* 合計金額を計算するメソッド[total]
  number:注文数 */
  total (number) {
    return this.price * number;
  }

  // 注文処理のメソッド
  takeOrder (number) {
    if (number > this.stock ) {
      console.log(`${this.name}は在庫不足です`);
    } else {
      const amount = this.total(number);   // 合計金額を計算
      this.stock -= number;                // 在庫を更新
      console.log(`${this.name}の購入代金は${amount}円`);
      console.log(`${this.name}の残りの在庫は${this.stock}個`);
    }
  }
}

// インスタンス化
const shampoo = new Product("シャンプー",800,10);
const coffee = new Product("コーヒー",500,5);

// 注文処理を実行(引数numberに注文数を代入)
shampoo.takeOrder(3);
coffee.takeOrder(7);