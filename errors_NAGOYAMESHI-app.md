Heroku デプロイ・Stripe設定

1. Herokuデプロイ時のクラッシュ原因

エラー: heroku[web.1]: Process exited with status 1 / H10 App crashed
原因: stripe.premium-plan-price-id が Heroku 環境変数および application-production.properties に未設定

対策:
application-production.properties に追記:
stripe.premium-plan-price-id=${STRIPE_PREMIUM_PLAN_PRICE_ID}

Herokuに環境変数追加:
heroku config:set STRIPE_PREMIUM_PLAN_PRICE_ID=price_xxxxxxxxxxxxx
Procfileで既に -Dspring.profiles.active=production を指定しているので追加設定は不要

再デプロイ後、heroku logs --tail で確認

2. application.properties と application-production.properties の整理

ローカル用 (application.properties)
```
stripe.api-key=sk_test_xxx
stripe.premium-plan-price-id=price_xxx
stripe.success-url=http://localhost:8080/success
stripe.cancel-url=http://localhost:8080/cancel
stripe.webhook-secret=whsec_xxx
```
gitignore対象で安全

Heroku用 (application-production.properties)
```
stripe.api-key=${STRIPE_API_KEY}
stripe.premium-plan-price-id=${STRIPE_PREMIUM_PLAN_PRICE_ID}
stripe.success-url=${STRIPE_SUCCESS_URL}
stripe.cancel-url=${STRIPE_CANCEL_URL}
stripe.webhook-secret=${STRIPE_WEBHOOK_SECRET}
```
環境変数から値を読み込む

3. Stripe Webhook設定

ローカルでWebhookを受信する場合のみ stripe.webhook-secret が必要
Webhookテストの処理内容:
Stripe側でイベント発生
ローカルサーバーで受信 (/webhook)
署名検証 (stripe.webhook-secret)
イベント内容に応じて処理（サブスク有効化/停止など）