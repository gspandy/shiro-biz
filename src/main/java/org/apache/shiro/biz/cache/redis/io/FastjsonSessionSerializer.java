/*
 * Copyright (c) 2010-2020, vindell (https://github.com/vindell).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.apache.shiro.biz.cache.redis.io;

import org.apache.shiro.biz.utils.GenericsUtils;

import com.alibaba.fastjson.JSONObject;

public class FastjsonSessionSerializer<T> implements SessionSerializer<T> {
	
	@Override
	public String serialize(T source) {
		return JSONObject.toJSONString(source);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T deserialize(String source) {
		Class<T> clazz = GenericsUtils.getSuperClassGenricType(getClass());
		return (T) JSONObject.parseObject(source, clazz);
	}
	
}
