/inject  Добавляет для тестирования данные истории
/currency-codes Возращает доступные валюты
/rates/{currencyFrom}/{currencyTo} просмот курса валют (* запрос сохраняеться в истории)
/history возращает историю (реализован фильтр по параметрам: 
 - dataRange - макс количество обьектов в ответе
 - fromCurrency 
 - toCurrency
 - fromDate
 - toDate)