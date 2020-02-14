/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com;

import com.alibaba.fastjson.JSON;
import com.serialization.JsonSerializationSchema;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer011;
import org.apache.flink.util.Collector;

import java.io.Serializable;
import java.util.Properties;

/**
 * Skeleton for a Flink Streaming Job.
 *
 * <p>For a tutorial how to write a Flink streaming application, check the
 * tutorials and examples on the <a href="https://flink.apache.org/docs/stable/">Flink Website</a>.
 *
 * <p>To package your application into a JAR file for execution, run
 * 'mvn clean package' on the command line.
 *
 * <p>If you change the name of the main class (with the public static void main(String[] args))
 * method, change the respective entry in the POM.xml file (simply search for 'mainClass').
 */
public class StreamingJob {

	public static void main(String[] args) throws Exception {
		// set up the streaming execution environment
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		/*
		 * Here, you can start creating your execution plan for Flink.
		 *
		 * Start with getting some data from the environment, like
		 * 	env.readTextFile(textPath);
		 *
		 * then, transform the resulting DataStream<String> using operations
		 * like
		 * 	.filter()
		 * 	.flatMap()
		 * 	.join()
		 * 	.coGroup()
		 *
		 * and many more.
		 * Have a look at the programming guide for the Java API:
		 *
		 * https://flink.apache.org/docs/latest/apis/streaming/index.html
		 *
		 */
		String kafka_host = "172.16.0.24:9092";
		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", kafka_host);
		properties.setProperty("group.id", "test");

		FlinkKafkaProducer<WordWithCount> myProducer = new FlinkKafkaProducer(
				kafka_host,            // broker list
				"my-topic",                  // target topic
				new JsonSerializationSchema());   // serialization schema

		FlinkKafkaConsumer<String> myConsumer = new FlinkKafkaConsumer<>(
				java.util.regex.Pattern.compile("test-topic-[0-9]"),
				new SimpleStringSchema(),
				properties);

		myConsumer.setStartFromGroupOffsets();

		DataStream<String> stream = env.addSource(myConsumer);

		/*orderId Count*/
		DataStream<WordWithCount> withCountDataStream = stream.flatMap(new FlatMapFunction<String, WordWithCount>() {
			@Override
			public void flatMap(String s, Collector<WordWithCount> collector) throws Exception {
				Order order = JSON.parseObject(s, Order.class);
				collector.collect(new WordWithCount("orderId", order.getOrderId(),1L));
			}
		})
				.keyBy("word")
				.timeWindow(Time.seconds(2),Time.seconds(2))
				.sum("count");

		withCountDataStream.print().setParallelism(1);

		DataStream<WordWithCount> countOrderIdDataStream = stream.flatMap(new FlatMapFunction<String, WordWithCount>() {
			@Override
			public void flatMap(String s, Collector<WordWithCount> collector) throws Exception {
				Order order = JSON.parseObject(s, Order.class);
				collector.collect(new WordWithCount("orderId", order.getOrderId(),1L));
			}
		})
				.keyBy("word")
				.timeWindow(Time.seconds(2),Time.seconds(2))
				.sum("count");

		countOrderIdDataStream.print().setParallelism(1);
		countOrderIdDataStream.addSink(myProducer);

		/*OrderStatus Count*/
		DataStream<WordWithCount> countStatusDataStream = stream.flatMap(new FlatMapFunction<String, WordWithCount>() {
			@Override
			public void flatMap(String s, Collector<WordWithCount> collector) throws Exception {
				Order order = JSON.parseObject(s, Order.class);
				collector.collect(new WordWithCount("OrderStatus", order.getOrderStatus()+"",1L));
			}
		})
				.keyBy("word")
				.timeWindow(Time.seconds(2),Time.seconds(2))
				.sum("count");

		countStatusDataStream.print().setParallelism(1);
		countStatusDataStream.addSink(myProducer);

		/*ShopId Count*/
		DataStream<WordWithCount> countShopIdDataStream = stream.flatMap(new FlatMapFunction<String, WordWithCount>() {
			@Override
			public void flatMap(String s, Collector<WordWithCount> collector) throws Exception {
				Order order = JSON.parseObject(s, Order.class);
				collector.collect(new WordWithCount("ShopId", order.getShopId()+"",1L));
			}
		})
				.keyBy("word")
				.timeWindow(Time.seconds(2),Time.seconds(2))
				.sum("count");

		countShopIdDataStream.print().setParallelism(1);
		countShopIdDataStream.addSink(myProducer);

		// execute program
		env.execute("Flink-kafka-read-message");
	}

	public static class WordWithCount{
		public String fieldName;
		public String word;
		public long count;
		public WordWithCount(){}
		public WordWithCount(String fieldName, String word, long count) {
			this.fieldName = fieldName;
			this.word = word;
			this.count = count;
		}

		@Override
		public String toString() {
			return "WordWithCount{" +
					"fieldName='" + fieldName + '\'' +
					"word='" + word + '\'' +
					", count=" + count +
					'}';
		}
	}

	public static class Order{
		private String orderId;
		private String shopId;
		private Integer orderStatus;
		private Long orderTime;
		private Double itemPrice;
		private String buyerAddress;

		public String getBuyerAddress() {
			return buyerAddress;
		}

		public void setBuyerAddress(String buyerAddress) {
			this.buyerAddress = buyerAddress;
		}

		public Double getItemPrice() {
			return itemPrice;
		}

		public void setItemPrice(Double itemPrice) {
			this.itemPrice = itemPrice;
		}

		public String getOrderId() {
			return orderId;
		}

		public void setOrderId(String orderId) {
			this.orderId = orderId;
		}

		public Integer getOrderStatus() {
			return orderStatus;
		}

		public void setOrderStatus(Integer orderStatus) {
			this.orderStatus = orderStatus;
		}

		public Long getOrderTime() {
			return orderTime;
		}

		public void setOrderTime(Long orderTime) {
			this.orderTime = orderTime;
		}

		public String getShopId() {
			return shopId;
		}

		public void setShopId(String shopId) {
			this.shopId = shopId;
		}

		@Override
		public String toString() {
			return "Order{" +
					"buyerAddress='" + buyerAddress + '\'' +
					", orderId='" + orderId + '\'' +
					", shopId='" + shopId + '\'' +
					", orderStatus=" + orderStatus +
					", orderTime=" + orderTime +
					", itemPrice=" + itemPrice +
					'}';
		}
	}
}
