#### 语句示例
* $query->andwhere(['or', ['like', self::COLUMN_NAME, $params['searchKey']]]);
* $query->orderBy(self::normalizeOrderBy($params));
#### id stirng to ObjectId[mongoDB]
* self::COLUMN_DREAM_ID => MongodbUtil::convertToMongoId($params[self::COLUMN_DREAM_ID])];