package com.example.nagoyameshi.form; // フォームクラスのパッケージ宣言

import org.hibernate.validator.constraints.Length; // 文字列長のバリデーション用
import jakarta.validation.constraints.Email;       // メール形式バリデーション用
import jakarta.validation.constraints.NotBlank;    // 空白禁止バリデーション用
import jakarta.validation.constraints.Pattern;     // 正規表現バリデーション用
import lombok.Data;                                 // lombokの@Dataアノテーションを使用

@Data // Getter/Setter、toString、equalsなど自動生成
public class SignupForm {
   
   @NotBlank(message = "氏名を入力してください。") // 空白不可の必須項目
   private String name;                            // 氏名
   
   @NotBlank(message = "フリガナを入力してください。") 
   private String furigana;                        // フリガナ
   
   @NotBlank(message = "郵便番号を入力してください。")
   @Pattern(regexp = "^[0-9]{7}$", message = "郵便番号は7桁の半角数字で入力してください。") // 7桁数字のみ許可
   private String postalCode;                      // 郵便番号
   
   @NotBlank(message = "住所を入力してください。")
   private String address;                         // 住所
   
   @NotBlank(message = "電話番号を入力してください。")
   @Pattern(regexp = "^[0-9]{10,11}$", message = "電話番号は10桁または11桁の半角数字で入力してください。") // 10～11桁の数字のみ
   private String phoneNumber;                     // 電話番号
   
   @Pattern(regexp = "^$|^[0-9]{8}$", message = "誕生日は8桁の半角数字で入力してください。") 
   // 空文字か8桁の数字(yyyyMMdd形式)のみ許可
   private String birthday;                        // 誕生日
   
   private String occupation;                      // 職業（任意）
   
   @NotBlank(message = "メールアドレスを入力してください。")
   @Email(message = "メールアドレスは正しい形式で入力してください。") // メール形式チェック
   private String email;                           // メールアドレス
   
   @NotBlank(message = "パスワードを入力してください。")
   @Length(min = 8, message = "パスワードは8文字以上で入力してください。") // 8文字以上必須
   private String password;                        // パスワード
   
   @NotBlank(message = "パスワード（確認用）を入力してください。")
   private String passwordConfirmation;            // パスワード確認用
}
