1. Web Site
* 事前にWebSiteに収集用jsタグを埋める
* ユーザがページに訪問する際に、requestを受け取る。aws load balancer と ec2(application server)を利用する。
** 1 Billions/day QPS ⇒ ピーク時qpsは 20000qps〜30000pqsと想定する。
* application server処理後、MessageをRabbitMQクラスタにプッシュする。

2. Message処理
* RabbitMQ クラスタを利用する予定。
* Messenegr ServerはRabbitMQ クラスタからmessageを取得し、処理する。下記の速報情報をリアルタイムでKVSに反映する。
** KVSは AeroSpikeを利用する予定
** 速報情報
*** PV数、UU数 (分、時間、日単位)
* 処理済データをs3にアップロードする。同時にsqsにメッセージをpushする。(1 min毎にアップロード)

3. ログ処理サーバー
* sqsからmessageを取得、s3ファイルパスからファイルをダウンロード。
* ファイルの詳細情報を集計して、KVSに反映する。
** user_agentからos、browserなどの集計情報
** siteごとの集計情報
* ログの情報を当日のDay Reportに反映する

4. ReportとLong Time Reportデータの集計
* 日ごとにReportからLong Time Reportに集計する
* Long Time Reportデータは過去のデータを全部保存する予定

5. User向け管理画面
* ハイトラフィック対応のため、Nginxを利用する予定。
* Nginxからリクエスト内容をWeb Application Serverに割り振る。
* リアルタイムの集計情報などはKVSから抽出する。
* 過去の集計情報を抽出する際に、Long Time Reportから取得する予定。
