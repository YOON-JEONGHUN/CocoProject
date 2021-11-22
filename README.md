# CocoProject
package com.mega.test05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity6 extends AppCompatActivity {
EditText etListAdd;
Button btnListAdd;
ListView listV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        setTitle("리스트뷰 테스트");

        etListAdd = findViewById(R.id.etListAdd);
        btnListAdd = findViewById(R.id.btnListAdd);

        ArrayList<String> list = new ArrayList<>();

        list.add("히어로즈");
        list.add("24시");
        list.add("로스트");
        list.add("빅뱅이론");

        ListView listV = findViewById(R.id.listV);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

        listV.setAdapter(adapter);

        btnListAdd.setOnClickListener(v -> {
            String data = etListAdd.getText().toString();
            list.add(data);
            adapter.notifyDataSetChanged();
        });
    }

}




// -------------------------- 조건에 맞는 값 set Text--------------------------------
        stRegion2 = findViewById(R.id.stRegion2);
        dpRegion2 = findViewById(R.id.dpRegion2);
        stList2 = findViewById(R.id.stList2);

        stRegion2.setText(stRegion);
        dpRegion2.setText(dpRegion);
        stList2.setText(stList);

//-------------------------- 버튼 클릭시, 전 화면으로 가기---------------------------------------------------
        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> {
            Intent intentBack = new Intent(BookTrain.this, BookMain.class);
            startActivity(intentBack);
        });



  Toast.makeText(getApplicationContext(), "출발역과 도착역이 동일합니다. 다시 선택해주세요.", Toast.LENGTH_SHORT).show();
=====================================================

  Intent intent = new Intent(BookMain.this, BookTrain.class);
                stRegion = findViewById(R.id.stRegion);
                dpRegion = findViewById(R.id.dpRegion);
                String stRegionNext = stRegion.getText().toString();
                String dpRegionNext = dpRegion.getText().toString();
                String sumPersonNext = sumPerson.getText().toString();
                intent.putExtra("stRegionNext", stRegionNext);
                intent.putExtra("dpRegionNext", dpRegionNext);
                intent.putExtra("sumPersonNext", sumPersonNext);
                intent.putExtra("dayNext", selectDay);
                startActivity(intent);
  ==========================================================================================


package com.app.mobile;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class MyGridAdapter extends BaseAdapter {

    Context context;

    MyGridAdapter(Context context1) {
        context = context1;
    }

    int[] posterId = {
            R.drawable.mov01,
            R.drawable.mov02,
            R.drawable.mov03,
            R.drawable.mov04,
            R.drawable.mov05,
            R.drawable.mov06,
            R.drawable.mov07,
            R.drawable.mov08,
            R.drawable.mov09
    };

    @Override
    public int getCount() {
        return posterId.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //gridView에 들어갈 하나하나의 layout을 만들어주는 메서드!
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //gridView에 들어갈 이미지뷰 객체를 만들어주어야 한다.
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(300, 300));
        imageView.setPadding(5, 5, 5, 5);
        // Button button = new Button(context);
        imageView.setImageResource(posterId[i]);
        return imageView;
    }
//기본 어탭터의 기능들을 내가 임의로 재정의해서 사용가능하다.


}


#Spring 설정

#pom.xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/maven-v4_0_0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>com.mega</groupId>
	<artifactId>finance</artifactId>
	<name>financeSpring</name>
	<packaging>war</packaging>
	<version>1.0.0-BUILD-SNAPSHOT</version>
	<properties>
		<java-version>1.8</java-version>
		<org.springframework-version>5.0.1.RELEASE</org.springframework-version>
		<org.aspectj-version>1.6.10</org.aspectj-version>
		<org.slf4j-version>1.6.6</org.slf4j-version>
	</properties>
	<dependencies>
		<!-- Spring -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>${org.springframework-version}</version>
			<exclusions>
				<!-- Exclude Commons Logging in favor of SLF4j -->
				<exclusion>
					<groupId>commons-logging</groupId>
					<artifactId>commons-logging</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc</artifactId>
			<version>${org.springframework-version}</version>
		</dependency>

		<!-- AspectJ -->
		<dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjrt</artifactId>
			<version>${org.aspectj-version}</version>
		</dependency>

		<!-- Logging -->
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-api</artifactId>
			<version>${org.slf4j-version}</version>
		</dependency>
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>jcl-over-slf4j</artifactId>
			<version>${org.slf4j-version}</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-log4j12</artifactId>
			<version>${org.slf4j-version}</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>log4j</groupId>
			<artifactId>log4j</artifactId>
			<version>1.2.15</version>
			<exclusions>
				<exclusion>
					<groupId>javax.mail</groupId>
					<artifactId>mail</artifactId>
				</exclusion>
				<exclusion>
					<groupId>javax.jms</groupId>
					<artifactId>jms</artifactId>
				</exclusion>
				<exclusion>
					<groupId>com.sun.jdmk</groupId>
					<artifactId>jmxtools</artifactId>
				</exclusion>
				<exclusion>
					<groupId>com.sun.jmx</groupId>
					<artifactId>jmxri</artifactId>
				</exclusion>
			</exclusions>
			<scope>runtime</scope>
		</dependency>

		<!-- @Inject -->
		<dependency>
			<groupId>javax.inject</groupId>
			<artifactId>javax.inject</artifactId>
			<version>1</version>
		</dependency>

		<!-- Servlet -->
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>servlet-api</artifactId>
			<version>2.5</version>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>javax.servlet.jsp</groupId>
			<artifactId>jsp-api</artifactId>
			<version>2.1</version>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>jstl</artifactId>
			<version>1.2</version>
		</dependency>

		<!-- Test -->
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.7</version>
			<scope>test</scope>
		</dependency>

// dependency

		<!-- 오라클 connector, driver설정 -->
		<!-- <dependency> <groupId>com.oracle</groupId> <artifactId>ojdbc6</artifactId> 
			<version>11.2.0.3</version> </dependency> -->
		
		<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>5.1.46</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/commons-dbcp/commons-dbcp -->
		<dependency>
			<groupId>commons-dbcp</groupId>
			<artifactId>commons-dbcp</artifactId>
			<version>1.4</version>
		</dependency>


		<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
		<dependency>
			<groupId>org.mybatis</groupId>
			<artifactId>mybatis</artifactId>
			<version>3.4.0</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis-spring -->
		<dependency>
			<groupId>org.mybatis</groupId>
			<artifactId>mybatis-spring</artifactId>
			<version>1.3.2</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/org.springframework/spring-jdbc -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-jdbc</artifactId>
			<version>5.0.1.RELEASE</version>
		</dependency>





	</dependencies>
	<build>
		<plugins>
			<plugin>
				<artifactId>maven-eclipse-plugin</artifactId>
				<version>2.9</version>
				<configuration>
					<additionalProjectnatures>
						<projectnature>org.springframework.ide.eclipse.core.springnature</projectnature>
					</additionalProjectnatures>
					<additionalBuildcommands>
						<buildcommand>org.springframework.ide.eclipse.core.springbuilder</buildcommand>
					</additionalBuildcommands>
					<downloadSources>true</downloadSources>
					<downloadJavadocs>true</downloadJavadocs>
				</configuration>
			</plugin>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>2.5.1</version>
				<configuration>
					<source>1.6</source>
					<target>1.6</target>
					<compilerArgument>-Xlint:all</compilerArgument>
					<showWarnings>true</showWarnings>
					<showDeprecation>true</showDeprecation>
				</configuration>
			</plugin>
			<plugin>
				<groupId>org.codehaus.mojo</groupId>
				<artifactId>exec-maven-plugin</artifactId>
				<version>1.2.1</version>
				<configuration>
					<mainClass>org.test.int1.Main</mainClass>
				</configuration>
			</plugin>
		</plugins>
	</build>
</project>




#root-context.xml
(원래 파일은 src/main/webapp/WEB-INF/appServlet)이지만 classpath: 로 경로 설정가능
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.3.xsd">

<context:property-placeholder location="classpath:db.properties"/>

<!--  dbcp -->
<bean id="dbcp" class="org.apache.commons.dbcp.BasicDataSource">
<!--  db connection pool을 만들기 위해, driver,url,user,pw -->
<!--  property태그는 각 변수의 set매서드를 호출해서 값을 넣어준다. -->
<!--  dbcp.setUrl("~~~~~") -->
<property name="driverClassName" value="${db.driver}"></property>
<property name="url" value="${db.url}"></property>
<property name="username" value="${db.user}"></property>
<property name="password" value="${db.password}"></property>
</bean>

<!-- mybatis와 관련된 설정이 많아서, 설정만 따로 해주는 클래스의 객체 -->
<bean id="config" class="org.mybatis.spring.SqlSessionFactoryBean">
	<property name="configLocation" value="classpath:mybatis-config.xml"></property>
	<property name="dataSource" ref="dbcp"></property>
</bean>


<!--  mybatis -->
<bean id="mybatis" class="org.mybatis.spring.SqlSessionTemplate">
<constructor-arg ref="config"></constructor-arg>
</bean>
</beans>







// 설정 파일 주소를 넣어줘야함, 또한 Namespaces를 등록해야한다.
// Namespaces를 등록하면  xmlns:context="http://www.springframework.org/schema/context" 이 생김
<context:property-placeholder location="classpath:db.properties"/> 

<!--  dbcp -->
<bean id="dbcp" class="org.apache.commons.dbcp.BasicDataSource">
<!--  db connection pool을 만들기 위해, driver,url,user,pw -->
<!--  property태그는 각 변수의 set매서드를 호출해서 값을 넣어준다. -->
<!--  dbcp.setUrl("~~~~~") -->
<property name="driverClassName" value="${db.driver}"></property>
<property name="url" value="${db.url}"></property>
<property name="username" value="${db.user}"></property>
<property name="password" value="${}"></property>
</bean>
${} ⇐  에 들어갈 값은 따로 파일을 만들어 설정할 수 있다


//   src/main/resources/db.properties ⇐ 주소
db.driver=com.mysql.jdbc.Driver
db.url=jdbc:mysql//localhost:3306/finance
db.user=root
db.password=1234




#mybatis-config.xml

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
	<typeAliases>
		<typeAlias type="com.mega.finance.FinanceVO" alias="financeVO"></typeAlias>
	</typeAliases>
	<mappers>
		<mapper resource="mapper/financeMapper.xml" />

	</mappers>
</configuration>


#HotelBookMapper.xml (mapper)
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="hotelBookMapper">		// 
	<insert id="create" parameterType="hotelBookVO">
		insert into HOTELBOOK values (#{hotelBookNo},#{hId},#{id},#{hName},#{hRegion},#{checkIn},#{checkOut},#{guestNum},#{roomSort},#{kName},#{birth},#{gender},#{eFirth},#{eLast},#{tel},#{email},#{total})
	</insert>
	 
	<select id="search" parameterType="hotelBookVO" resultType="hotelBookVO">
		select * from FMEMBER where ID = #{id}
	</select>
	
	<select id="searchOne" parameterType="hotelBookVO" resultType="hotelBookVO">
		select * from HOTELBOOK where HOTELBOOKNO = #{hotelBookNo}
	</select>
	
	<update id="couponUp" parameterType="hotelBookVO">
		update FMEMBER set COUPON = #{coupon} where ID = #{id}
	</update>
	
</mapper> 


