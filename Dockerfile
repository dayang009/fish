# 使用基础镜像 centos:7.9
# docker build -t your_image_name:tag .
FROM centos:7.9

# 复制本地JDK和Maven安装包到镜像
COPY jdk-8uXXX-linux-x64.tar.gz /tmp/
COPY apache-maven-3.6.3-bin.tar.gz /tmp/

# 安装必要的依赖
RUN yum -y update && \
    yum -y install tar && \
    yum -y install which

# 安装 JDK
RUN mkdir /usr/lib/jvm && \
    tar -zxvf /tmp/jdk-8uXXX-linux-x64.tar.gz -C /usr/lib/jvm/ && \
    rm /tmp/jdk-8uXXX-linux-x64.tar.gz

# 设置JAVA_HOME 环境变量
ENV JAVA_HOME /usr/lib/jvm/jdk1.8.0_XXX

# 安装 Maven
ENV MAVEN_VERSION 3.6.3
ENV MAVEN_HOME /usr/share/maven

RUN mkdir /usr/share/maven && \
    tar -zxvf /tmp/apache-maven-${MAVEN_VERSION}-bin.tar.gz -C /usr/share/ && \
    ln -s /usr/share/apache-maven-${MAVEN_VERSION} /usr/share/maven && \
    ln -s /usr/share/maven/bin/mvn /usr/bin/mvn && \
    rm /tmp/apache-maven-${MAVEN_VERSION}-bin.tar.gz

# 设置 Maven 环境变量
ENV PATH ${MAVEN_HOME}/bin:$PATH

# 设置工作目录
WORKDIR /workspace

# 默认命令
CMD ["/bin/bash"]
