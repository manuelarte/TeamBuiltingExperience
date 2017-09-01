package org.manuel.teambuilting.experience.comments;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author manuel.doncel.martos
 * @since 1-9-2017.
 */
public class IncomingPlayerCommentDtoTest {

	private static ObjectMapper mapper;

	@BeforeAll
	public static void beforeAll() {
		mapper = new ObjectMapper().findAndRegisterModules();
	}

	@Test
	@DisplayName("Serialize an IncomingPlayerComment")
	public void testSerializeIncomingPlayerCommentDto() throws JsonProcessingException {
		final IncomingPlayerCommentDto expected = IncomingPlayerCommentDto.builder().playerId(BigInteger.ONE).reason(CommentReason.KNOW)
			.comment(UUID.randomUUID().toString()).build();
		final JSONObject jsonObject = new JSONObject(mapper.writeValueAsString(expected));
		assertEquals(expected.getPlayerId().toString(), jsonObject.get("playerId").toString());
		assertEquals(expected.getComment(), jsonObject.get("comment").toString());
		assertEquals(expected.getReason(), CommentReason.valueOf(jsonObject.get("reason").toString()));
	}

	@Test
	@DisplayName("Deserialize an IncomingPlayerComment")
	public void testDeserializeIncomingPlayerCommentDto() throws IOException {
		final Map<String, Object> map = new HashMap<>();
		map.put("id", UUID.randomUUID().toString());
		map.put("playerId", BigInteger.ONE);
		map.put("comment", UUID.randomUUID().toString());
		map.put("reason", CommentReason.PLAYED_AGAINST);

		final IncomingPlayerCommentDto actual = mapper.readValue(new JSONObject(map).toString(), IncomingPlayerCommentDto.class);
		assertEquals(map.get("id").toString(), actual.getId());
		assertEquals(map.get("playerId"), actual.getPlayerId());
		assertEquals(map.get("comment"), actual.getComment());
		assertEquals(map.get("reason"), actual.getReason());
	}

}
