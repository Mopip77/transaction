# spring transaction failed reason
https://github.com/MagicienDeCode/Transactional

# transaction Inherited
应该多去研究一下代码，org.springframework.transaction.interceptor.AbstractFallbackTransactionAttributeSource#computeTransactionAttribute 这个方法是计算注解的逻辑 注解解析事务 这个注解的解析过程是非常头疼的！4.0x 版本，5.x 版本的处理不一样 主要差距在 这个类 org.springframework.transaction.annotation.SpringTransactionAnnotationParser 你必须关心这个，不然使用的时候难免采坑！最好的方式就是直接在具体的类上标识事务注解！不会采坑！ 4.x 版本 AnnotatedElementUtils.getMergedAnnotationAttributes 只查找当前元素以及元注解(如果是类有继承 @Inherited注解 继承) 5.x 版本 AnnotatedElementUtils.findMergedAnnotationAttributes 查找当前类->遍历元注解查找当前注解->查找当前元素的所有接口->查找当前父类方法 因此如果5.x版本 方法可以类似继承 按照 AnnotatedElementUtils.findMergedAnnotationAttributes 的规则来办事，首先是当前方法、然后是当前遍历元注解，然后是接口(元注解遍历)、最后是父类接口(元注解遍历) 最先找到的那个就生效！ 4.x 版本就不会生效了方法的继承 AnnotatedElementUtils.getMergedAnnotationAttributes 只查找当前元素以及元注解(如果是类有继承 @Inherited注解 继承) 因此这个和版本有关系 ，如果找不到就会查询类上面的事务注解了...造成各种你意想不到的问题！最好放置在目标类上面去！

# annotation
https://blog.csdn.net/liupengtx/article/details/84714238?utm_medium=distribute.pc_relevant.none-task-blog-baidujs_baidulandingword-0&spm=1001.2101.3001.4242
