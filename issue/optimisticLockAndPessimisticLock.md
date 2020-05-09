### 乐观锁
* 假设数据操作时，不会发生数据冲突。
* 乐观锁不是基于数据库，而是基于数据存储实现，此处考虑的是`版本控制`实现。
* 实现方式：
给相应数据表增加版本控制字段 `version`, 读取数据时获得 version = versionValue, 更新数据时，判断表字段 version ？= versionValue， 是则更新数据，并 version 自增 1，否则失败
* 可能产生脏读

### 悲观锁
悲观锁大多数基于数据库锁机制(如行锁，表锁)实现，不存在数据冲突，不适合并发量大的场景
> 例如： `select * from account where name="Erica" for update`
