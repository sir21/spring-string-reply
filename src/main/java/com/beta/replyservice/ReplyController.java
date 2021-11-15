package com.beta.replyservice;

import com.beta.replyservice.exception.InvalidInput;
import com.beta.replyservice.model.Error;
import com.beta.replyservice.model.Reply;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class ReplyController {

	@GetMapping(value = {"/reply", "/v2/reply"})
	public ReplyMessage replying() {
		return new ReplyMessage("Message is empty");
	}

	@GetMapping("/reply/{message}")
	public ReplyMessage replying(@PathVariable String message) {
		return new ReplyMessage(message);
	}

	@GetMapping("v2/reply/{rule}-{string}")
	public ResponseEntity<Object> replyingV2(@PathVariable String rule, @PathVariable String string) throws Exception {
		try {
			return new ResponseEntity<>(new Reply(new ReplyMessage(rule, string).getMessage()), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(new Error(ex.getMessage()), ex.getMessage().equals("Invalid input") ? HttpStatus.BAD_REQUEST : HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}