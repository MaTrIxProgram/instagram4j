/**
 * Copyright (C) 2016 Bruno Candido Volpato da Cunha (brunocvcunha@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.brunocvcunha.instagram4j.requests;

import java.util.LinkedHashMap;
import java.util.Map;

import org.brunocvcunha.instagram4j.requests.payload.InstagramInboxResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

/**
 * Inbox Request
 * 
 * @author Bruno Candido Volpato da Cunha
 *
 */
@RequiredArgsConstructor
@AllArgsConstructor
public class InstagramGetInboxRequest extends InstagramGetRequest<InstagramInboxResult> {

	private String cursor;

	@Override
	public String getUrl() {

		String baseUrl = "direct_v2/inbox/?";
		return baseUrl;
	}

	@Override
	@SneakyThrows
	public String getPayload() {
		String payloadJson = null;
		Map<String, Object> likeMap = new LinkedHashMap<>();
		likeMap.put("persistentBadging", true);
		likeMap.put("use_unified_inbox", true);
		
		if (cursor != null && !cursor.isEmpty()) {			
			likeMap.put("cursor", cursor);			
		}

		ObjectMapper mapper = new ObjectMapper();
		payloadJson = mapper.writeValueAsString(likeMap);
		System.out.println(payloadJson);
		return payloadJson;
	}

	@Override
	@SneakyThrows
	public InstagramInboxResult parseResult(int statusCode, String content) {
		System.out.println(content);
		return parseJson(statusCode, content, InstagramInboxResult.class);
	}

}
