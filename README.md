## サンプルで実現している内容

- docker(環境構築)
  - oracleのコンテナ化、立ち上げ
  - gitlab、gitlab-runnerのコンテナ化、立ち上げ
  - gitlabデータ永続化
- テスト
  - gradlewを利用したビルド
  - junit5自動テスト
  - maven-centralを利用したoracleとの接続
- DevOps(テスト自動化)
  - docker-executorを利用した再現可能な環境構築、テストexample
  - code_qualityによる静的解析の実行
  - Artifact保管によるテストレポートの取得
  - Pagesによるテストレポートのブラウザ参照