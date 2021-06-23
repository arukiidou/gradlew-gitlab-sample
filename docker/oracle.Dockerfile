FROM oracle/database:11.2.0.2-xe 

RUN yum -y install vi
RUN echo 'TZ="Asia/Tokyo"' > /etc/sysconfig/clock
RUN cp /usr/share/zoneinfo/Asia/Tokyo /etc/localtime
RUN echo 'LANG="ja_JP.UTF-8"' > /etc/sysconfig/i18n
RUN echo 'LC_CTYPE="ja_JP.utf8"' >> /etc/sysconfig/i18n
RUN yum reinstall -y glibc-common
RUN yum reinstall -y glibc
RUN localedef -f UTF-8 -i ja_JP ja_JP.UTF-8

# ポート番号1521で起動
EXPOSE 1521
