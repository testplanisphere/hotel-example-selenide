# hotel-example-selenide-ja

![selenide-ja](https://github.com/testplanisphere/hotel-example-selenide-ja/workflows/selenide-ja/badge.svg)

このプロジェクトはテスト自動化学習のためのサンプルコードです。

### テスト対象

https://hotel.testplanisphere.dev/ja/

### 概要

#### プログラミング言語

* Java

#### 自動化フレームワーク

* [Selenide](https://selenide.org/)

#### テスティングフレームワーク

* [JUnit 5](https://junit.org/junit5/)

#### ビルドツール

* [Gradle](https://gradle.org/)

#### 静的解析ツール

* [Checkstyle](https://checkstyle.sourceforge.io/)

### 実行方法

#### 必須環境

* JDK 11
* Google Chrome

#### テスト・静的解析の実行

##### Windows

```
gradlew.bat clean check
```

##### macOS/Linux

```
./gradlew clean check
```

### 変更履歴

#### v2020.6.0 (2020-06-30)

* [#11](https://github.com/testplanisphere/hotel-example-selenide-ja/pull/11) Bump Gradle from 6.4.1 to 6.5.1
* [#12](https://github.com/testplanisphere/hotel-example-selenide-ja/pull/12) Bump selenide from 5.12.1 to 5.12.2

#### v1.1.0 (2020-05-31)

* [#3](https://github.com/testplanisphere/hotel-example-selenide-ja/pull/3) Selenideを5.12.1にアップデート
* [#5](https://github.com/testplanisphere/hotel-example-selenide-ja/pull/5) Dependabotを導入
* [#8](https://github.com/testplanisphere/hotel-example-selenide-ja/pull/8) Github actions/cacheをv2にアップデート
* [#9](https://github.com/testplanisphere/hotel-example-selenide-ja/pull/9) Gradleを6.4.1にアップデート

#### v1.0.0 (2020-04-29)

* 正式リリース
