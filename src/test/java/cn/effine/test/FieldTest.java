package cn.effine.test;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by effine on 19/10/2016.
 */
public class FieldTest {

	/**
	 * 将实体转化为Map
	 * 
	 * @author effine[zhangyafei@co-mall.com]
	 *
	 * @param bean
	 *            待转换的实体
	 * @param isContainNull
	 *            返回值集合是否包含null属性
	 * @return 实体对应Map(key为属性，value为属性值)
	 */
	private Map<String, Object> beanToMap(OrderDataConsistency bean,
			boolean isContainNull) {
		if (null != bean) {
			Field[] fields = bean.getClass().getDeclaredFields();
			if (null != fields && fields.length > 0) {
				Map<String, Object> map = new HashMap<>();
				for (Field field : fields) {
					field.setAccessible(true);
					try {
						Object value = field.get(bean);
						if (!isContainNull && null == value)
							continue;

						if (field.getGenericType().toString()
								.equals("class java.util.Date")) {
							SimpleDateFormat df = new SimpleDateFormat(
									"yyyy-MM-dd HH:mm:ss");
							map.put(field.getName(), df.format(new Date()));
							continue;
						}
						map.put(field.getName(), field.get(bean));
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}
				return map;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		OrderDataConsistency test = new OrderDataConsistency();
		test.setCreateTime(new Date());
		FieldTest t = new FieldTest();
		Map<String, Object> map = t.beanToMap(test, false);
		System.out.println(map);
	}
}
