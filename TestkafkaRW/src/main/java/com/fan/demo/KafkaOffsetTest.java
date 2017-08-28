package com.fan.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kafka.api.PartitionOffsetRequestInfo;
import kafka.common.TopicAndPartition;
import kafka.javaapi.OffsetRequest;
import kafka.javaapi.OffsetResponse;
import kafka.javaapi.PartitionMetadata;
import kafka.javaapi.TopicMetadata;
import kafka.javaapi.TopicMetadataRequest;
import kafka.javaapi.TopicMetadataResponse;
import kafka.javaapi.consumer.SimpleConsumer;

public class KafkaOffsetTest {
	public static void main(String[] args) {

		String ip = "127.0.0.1";
		int port = 3002;

		List<String> ls = new ArrayList<String>();
		ls.add("test");

		TopicMetadataRequest topicMetadataRequest = new TopicMetadataRequest(ls);
		SimpleConsumer consumer = new SimpleConsumer(ip, port, 30000, 1024 * 1024, "1");
		TopicMetadataResponse resp = consumer.send(topicMetadataRequest);
		List<TopicMetadata> metaData = resp.topicsMetadata();

		// if (metaData == null) {
		// System.out.println("无队列");
		// return;
		// }
		// for (TopicMetadata topicMetadata : metaData) {
		// for (PartitionMetadata partitionMetadata :
		// topicMetadata.partitionsMetadata()) {
		//
		// System.out.println(partitionMetadata.partitionId());
		// System.out.println(partitionMetadata.sizeInBytes());
		// System.out.println(partitionMetadata.errorCode());
		// System.out.println(partitionMetadata.leader().host());
		// System.out.println(partitionMetadata.leader().port());
		// System.out.println(partitionMetadata.leader().connectionString());
		// System.out.println(partitionMetadata.leader().id());
		// }
		//
		// }
		TopicAndPartition topicAndPartition = new TopicAndPartition("test", 1);
		Map<TopicAndPartition, PartitionOffsetRequestInfo> requestInfo = new HashMap<TopicAndPartition, PartitionOffsetRequestInfo>();
		requestInfo.put(topicAndPartition, new PartitionOffsetRequestInfo(kafka.api.OffsetRequest.LatestTime(), 1));
		OffsetRequest request = new OffsetRequest(requestInfo, kafka.api.OffsetRequest.CurrentVersion(), "1");
		OffsetResponse response = consumer.getOffsetsBefore(request);

		long[] offsets = response.offsets("test", 1);

		for (long t : offsets) {
			System.out.println(t);
		}
	}

}
