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

#### v2020.9.0 (2020-09-30)

* [#25](https://github.com/testplanisphere/hotel-example-selenide-ja/pull/25) Bump junit-jupiter-api from 5.6.2 to 5.7.0
* [#26](https://github.com/testplanisphere/hotel-example-selenide-ja/pull/26) Bump junit-jupiter-engine from 5.6.2 to 5.7.0
* [#27](https://github.com/testplanisphere/hotel-example-selenide-ja/pull/27) Bump selenide from 5.14.2 to 5.15.0

#### v2020.8.0 (2020-08-31)

* [#21](https://github.com/testplanisphere/hotel-example-selenide-ja/pull/21) Bump Gradle from 6.5.1 to 6.6.1
* [#22](https://github.com/testplanisphere/hotel-example-selenide-ja/pull/22) Add MavenCentral repository
* [#23](https://github.com/testplanisphere/hotel-example-selenide-ja/pull/23) Bump selenide from 5.13.0 to 5.14.2

#### v2020.7.0 (2020-07-26)

* [#14](https://github.com/testplanisphere/hotel-example-selenide-ja/pull/14) Bump selenide from 5.12.2 to 5.13.0
* [#15](https://github.com/testplanisphere/hotel-example-selenide-ja/pull/15) fix site title
* [#16](https://github.com/testplanisphere/hotel-example-selenide-ja/pull/16) Update Dependabot config file
* [#17](https://github.com/testplanisphere/hotel-example-selenide-ja/pull/17) update actions and dependabot
* [#18](https://github.com/testplanisphere/hotel-example-selenide-ja/pull/18) fix branch name
* [#19](https://github.com/testplanisphere/hotel-example-selenide-ja/pull/19) move to ja site

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
