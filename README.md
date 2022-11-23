# hotel-example-selenium4-java-ja

[![selenium4-java-ja](https://github.com/testplanisphere/hotel-example-selenium4-java-ja/actions/workflows/test.yml/badge.svg)](https://github.com/testplanisphere/hotel-example-selenium4-java-ja/actions/workflows/test.yml)

このプロジェクトはテスト自動化学習のためのサンプルコードです。

### テスト対象

https://hotel.testplanisphere.dev/ja/

### 概要

#### プログラミング言語

* Java

#### 自動化フレームワーク

* [Selenium WebDriver](https://www.selenium.dev/)

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
gradlew.bat clean test
```

##### macOS/Linux

```
./gradlew clean test
```

### 変更履歴

#### v2021.3.0 (2021-03-22)

* First release
