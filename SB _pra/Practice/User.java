// [Entityクラス]では、DBのカラム名とのマッピングを定義する
package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String name;
    private String email;
}
