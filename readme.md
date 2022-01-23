/inject  Добавляет для тестирования истории данные в БД<br>
/currency-codes Возращает доступные валюты <br>
/rates/{currencyFrom}/{currencyTo} просмот курса валют (* запрос сохраняеться в истории) <br>
/history? возращает историю (реализован фильтр по параметрам:  <br>
 - dataRange - макс количество обьектов в ответе <br>
 - currencyFrom=($availableCode) <br>
 - currencyTo={$availableCode) <br>
 - fromDate={HHHH-mm-dd}<br>
 - toDate={HHHH-mm-dd}) <br>