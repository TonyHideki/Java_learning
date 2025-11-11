package 計算機;//スッキリわかるJava入門　第2章

import java.util.Scanner;//ライブラリの読み込み,キーボードから入力を受け取るために必要。
/*コンソール電卓のその1(ループ無し)*/

public class calculator1 {// クラス宣言,Javaの基本構文。「calculator1」という名前のプログラム。
    public static void main(String[] args) {// メインメソッド,Javaプログラムの「最初に実行される部分」。
        Scanner scanner = new Scanner(System.in);// 入力準備,コンソール入力を受け付けるためのScannerオブジェクトを作成。

        try {
            System.out.println("1つ目の数値を入力してください：");
            double num1 = scanner.nextDouble();// 数値入力,ユーザーからの入力をdouble型で受け取る。

            System.out.println("2つ目の数値を入力してください：");
            double num2 = scanner.nextDouble();

            System.out.println("演算子を入力してください：");
            String operator = scanner.next();// 文字列入力,ユーザーからの入力をString型で受け取る。

            double result = 0;// 結果変数,計算結果を格納するための変数。

            switch (operator) { // 分岐処理,文字列入力された演算子によって実行する処理を分ける。
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) { // 条件チェック,「0で割る」操作を防ぐ。
                        System.out.println("ゼロで割ることはできません。");
                        return;
                    }
                    result = num1 / num2;
                    break;
                default:
                    System.out.println("無効な演算子です。");
                    return;
            }
            System.out.println("結果: " + result);
        } catch (Exception e) { // 例外処理,エラーが発生した場合の処理。
            System.out.println("エラー: " + e.getMessage());
        } finally {
            scanner.close(); // 入力のクローズ,キーボード入力のリソースを解放。
            System.out.println("計算機を終了します。");
        }

    }
}
