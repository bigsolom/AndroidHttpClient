AndroidHttpClient
===================

A simple Android HTTP Client built on top of Apache HTTP Client

How to use:
-----------

Use the `SimpleRequest` class, implement the `processResponse(String response)` method which gets the response as a string from the server

	new SimpleRequest(SimpleRequest.HTTP_GET) {
		public void processResponse(String response) {
			System.out.println(response);
		}
	}.doRequest("http://www.example.com/posts.json", new HashMap<String, String>(), handler);

`SimpleRequest` constructor takes an integer which represent whether the request will be GET or POST, use `SimpleRequest.HTTP_GET` or `SimpleRequest.HTTP_POST`.

`handler` is a `Handler` object that must initialized in your activity.

The Hashmap is a map of String keys and object that contains request parameters.
