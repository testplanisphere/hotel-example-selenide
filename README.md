# hotel-example-selenide

![selenide](https://github.com/testplanisphere/hotel-example-selenide/workflows/selenide/badge.svg)

このプロジェクトはテスト自動化学習のためのサンプルコードです。
This project is example codes for learning test automation.

### テスト対象 / Test Object

https://hotel.testplanisphere.dev/

### 概要 / Overview

#### プログラミング言語 / Programming Language

* Java

#### 自動化フレームワーク / Automation Framework

* [Selenide](https://selenide.org/)

#### テスティングフレームワーク / Testing Framework

* [JUnit 5](https://junit.org/junit5/)

#### ビルドツール / Build Tool

* [Gradle](https://gradle.org/)

#### 静的解析ツール / Lint Tool

* [Checkstyle](https://checkstyle.sourceforge.io/)

### 実行方法 / How to Run

#### 必須環境 / Requirements

* JDK 11
* Google Chrome

#### テスト・静的解析の実行 / Run Tests and lint

##### Windows

```
gradlew.bat clean check
```

##### macOS/Linux

```
./gradlew clean check
```

### 変更履歴 / Changelog

#### v1.1.0 (2020-05-31)

* [#3](https://github.com/testplanisphere/hotel-example-selenide/pull/3) Selenideを5.12.1にアップデート
* [#5](https://github.com/testplanisphere/hotel-example-selenide/pull/5) Dependabotを導入
* [#8](https://github.com/testplanisphere/hotel-example-selenide/pull/8) Github actions/cacheをv2にアップデート
* [#9](https://github.com/testplanisphere/hotel-example-selenide/pull/9) Gradleを6.4.1にアップデート

#### v1.0.0 (2020-04-29)

* 正式リリース
