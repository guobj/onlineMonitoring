<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("path", path);
pageContext.setAttribute("basePath", basePath);
%>
<!DOCTYPE HTML>
<html>
<head lang="en">
    <base href="${basePath }">
    <meta charset="UTF-8">
    <title></title>
    <link href="css/xinxi.css" rel="stylesheet" type="text/css">
    <script src="js/jquery.js"></script>
    <script src="js/xinxi.js" ></script>
    <script src="js/jilian.js" type="text/javascript"></script>
</head>
<body>
<div id="tit">
    <p>山东省病虫害物联网监测系统</p>
    <ul>
        <li style="width:8.7%;"><img src="img/yh.png"></li>
        <li>欢迎您，Admin. 今天是<span id="year"></span>年<span id="month"></span>月<span id="dat"></span>日，星期<span id="day"></span>.</li>
        <li style=" margin-top:11px; width:100%; background:url(img/sbg.png) no-repeat; height:32px;">
            <ul class="three">
                <a href="#"><img src="img/fz.png">&nbsp;返回首页</a>
                <a href="#"><img src="img/bg2.png">&nbsp;个人信息</a>
                <a href="#"><img src="img/gb.png" style="margin-top:7px;">&nbsp;退出系统</a>
            </ul>
        </li>
    </ul>
</div>
<div id="caidan">
    <ul>
        <li class="tit">
            <img src="img/gl1.png">
            &nbsp;管理菜单
        </li>
        <li class="cdq">
            <img src="img/bf.png">
            <a>功能菜单区</a>
        </li>
        <div class="cd_4">
            <ul id="ceNav">
                <li>
                    <a href="#" class="a_1"> <img src="img/xt3.png">&nbsp;站点管理</a>
                    <ul class="zdgl" id="c1">
                        <li><a href="#" class="a_2"><img src="img/xxtb.jpg">&nbsp;站点信息查询</a></li>
                        <li><a href="#" class="a_2"><img src="img/xxtb.jpg">&nbsp;站点配置管理</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#" class="a_1"><img src="img/xt3.png">&nbsp;监测设备管理</a>
                    <ul class="zdgl" id="c2">
                        <li><a href="#" class="a_2"><img src="img/xxtb.jpg">&nbsp;设备信息管理</a></li>
                        <li><a href="#" class="a_2"><img src="img/xxtb.jpg">&nbsp;设备状态查看</a></li>
                        <li><a href="#" class="a_2"><img src="img/xxtb.jpg">&nbsp;设备信息查询</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#" class="a_1"><img src="img/xt3.png">&nbsp;监测数据管理</a>
                    <ul class="zdgl" id="c3">
                        <li><a href="#" class="a_2"><img src="img/xxtb.jpg">&nbsp;实时数据查看</a></li>
                        <li><a href="#" class="a_2"><img src="img/xxtb.jpg">&nbsp;历史数据查询功能</a></li>
                    </ul>
                </li>
                <!--<li>-->
                <!--<a href="#" class="a_1"><img src="img/xt3.png">&nbsp;系统管理</a>-->
                <!--<ul class="zdgl" id="c4">-->
                <!--<li><a href="#" class="a_2"><img src="img/xxtb.jpg">&nbsp;用户管理</a></li>-->
                <!--<li><a href="#" class="a_2"><img src="img/xxtb.jpg">&nbsp;权限管理</a></li>-->
                <!--</ul>-->
                <!--</li>-->
            </ul>
        </div>
    </ul>
</div>


<div id="content_r">
    <li class="tit"><p class="xx"><img src="img/zb.png">&nbsp;当前位置&nbsp;:&nbsp;<span id="zb1">首页</span> > <span id="zb2">站点信息</span> > <span id="zb3">站点配置管理</span></p></li>
    <div class="gn">
        <form>
            <span class="span1">监测站名称：<input type="text" placeholder="不限" ></span>
            <span class="span1">资金来源：
                <select class="select1">
                    <option>不限</option>
                    <option value="">省资金</option>
                    <option value="国家资金">国家资金</option>
                    <option value="其他">其他</option>
                </select>
            </span>
            <span class="span1">监测站类型：
                <select class="select1">
                    <option>不限</option>
                    <option value="新建重点监测站">新建重点监测站</option>
                    <option value="改建重点监测站">改建重点监测站</option>
                    <option value="新建普通监测站">新建普通监测站</option>
                    <option value="改建普通监测站">改建普通监测站</option>
                </select>
            </span>
            <span  class="span1">监测站区域：
                        <select id="s_province" name="s_province"></select>
                        <select id="s_city" name="s_city" ></select>
                        <script type="text/javascript">_init_area();</script>
                </span>
            <span class="span1">网关类型：
                <select class="select1">
                    <option>不限</option>
                    <option value="NZ2000">NZ2000</option>
                    <option value="NZ1000">NZ1000</option>
                </select>
            </span>

            <input type="button"  value="查询" class="cx"> <input type="button" value="添加监测站" class="btn1" onclick="tianjia()"/>
        </form>
    </div>


    <h4>数据列表：</h4>

    <div id="xq">
        <table  id="bg">
            <thead>
            <tr>
                <td class="t3">监测站名称</td>
                <td class="t2">监测站编码</td>
                <td class="t4">建设内容</td>
                <td class="t5">监测站类型</td>
                <td class="t6">查看</td>
                <td class="t7">删除</td>
                <td class="t8">配置</td>
                <td class="t9">修改</td>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td class="t3">监测站名称</td>
                <td class="t2">监测站编码</td>
                <td class="t4">建设内容</td>
                <td class="t5">监测站类型</td>
                <td class="t6"><input type="button" value="查看" class="input1" onclick="chakan(this)"></td>
                <td class="t7"><input type="button" value="删除"  class="input2" ></td>
                <td class="t8"><input type="button" value="配置" class="input1" onclick="peizhi(this)"></td>
                <td class="t9"><input type="button" value="修改"  class="input1"  onclick="xiugai(this)"></td>
            </tr>
            <tr>
                <td class="t3">监测站名称</td>
                <td class="t2">监测站编码</td>
                <td class="t4">建设内容</td>
                <td class="t5">监测站类型</td>
                <td class="t6"><input type="button" value="查看" class="input1" onclick="chakan(this)"></td>
                <td class="t7"><input type="button" value="删除"  class="input2"></td>
                <td class="t8"><input type="button" value="配置" class="input1" onclick="peizhi(this)"></td>
                <td class="t9"><input type="button" value="修改"  class="input1"  onclick="xiugai(this)"></td>
            </tr>

            <tr>
                <td class="t3">监测站名称</td>
                <td class="t2">监测站编码</td>
                <td class="t4">建设内容</td>
                <td class="t5">监测站类型</td>
                <td class="t6"><input type="button" value="查看" class="input1" onclick="chakan(this)"></td>
                <td class="t7"><input type="button" value="删除"  class="input2"></td>
                <td class="t8"><input type="button" value="配置" class="input1" onclick="peizhi(this)"></td>
                <td class="t9"><input type="button" value="修改"  class="input1"  onclick="xiugai(this)"></td>
            </tr>
            <tr>
                <td class="t3">监测站名称</td>
                <td class="t2">监测站编码</td>
                <td class="t4">建设内容</td>
                <td class="t5">监测站类型</td>
                <td class="t6"><input type="button" value="查看" class="input1" onclick="chakan(this)"></td>
                <td class="t7"><input type="button" value="删除"  class="input2"></td>
                <td class="t8"><input type="button" value="配置" class="input1" onclick="peizhi(this)"></td>
                <td class="t9"> <input type="button" value="修改"  class="input1"  onclick="xiugai(this)"></td>
            </tr>
            <tr>
                <td class="t3">监测站名称</td>
                <td class="t2">监测站编码</td>
                <td class="t4">建设内容</td>
                <td class="t5">监测站类型</td>
                <td class="t6"><input type="button" value="查看" class="input1" onclick="chakan(this)"></td>
                <td class="t7"><input type="button" value="删除"  class="input2"></td>
                <td class="t8"><input type="button" value="配置" class="input1" onclick="peizhi(this)"></td>
                <td class="t9"><input type="button" value="修改"  class="input1"  onclick="xiugai(this)"></td>
            </tr>
            <tr>
                <td class="t3">监测站名称</td>
                <td class="t2">监测站编码</td>
                <td class="t4">建设内容</td>
                <td class="t5">监测站类型</td>
                <td class="t6"><input type="button" value="查看" class="input1" onclick="chakan(this)"></td>
                <td class="t7"><input type="button" value="删除"  class="input2"></td>
                <td class="t8"><input type="button" value="配置" class="input1" onclick="peizhi(this)"></td>
                <td class="t9"><input type="button" value="修改"  class="input1"  onclick="xiugai(this)"></td>
            </tr>
            <tr>
                <td class="t3">监测站名称</td>
                <td class="t2">监测站编码</td>
                <td class="t4">建设内容</td>
                <td class="t5">监测站类型</td>
                <td class="t6"><input type="button" value="查看" class="input1" onclick="chakan(this)"></td>
                <td class="t7"><input type="button" value="删除"  class="input2"></td>
                <td class="t8"><input type="button" value="配置" class="input1" onclick="peizhi(this)"></td>
                <td class="t9"><input type="button" value="修改"  class="input1"  onclick="xiugai(this)"></td>
            </tr>
            <tr>
                <td class="t3">监测站名称</td>
                <td class="t2">监测站编码</td>
                <td class="t4">建设内容</td>
                <td class="t5">监测站类型</td>
                <td class="t6"><input type="button" value="查看" class="input1" onclick="chakan(this)"></td>
                <td class="t7"><input type="button" value="删除"  class="input2"></td>
                <td class="t8"><input type="button" value="配置" class="input1" onclick="peizhi(this)"></td>
                <td class="t9"><input type="button" value="修改"  class="input1"  onclick="xiugai(this)"></td>
            </tr>
            <tr>
                <td class="t3">监测站名称</td>
                <td class="t2">监测站编码</td>
                <td class="t4">建设内容</td>
                <td class="t5">监测站类型</td>
                <td class="t6"><input type="button" value="查看" class="input1" onclick="chakan(this)"></td>
                <td class="t7"><input type="button" value="删除"  class="input2"></td>
                <td class="t8"><input type="button" value="配置" class="input1" onclick="peizhi(this)"></td>
                <td class="t9"><input type="button" value="修改"  class="input1"  onclick="xiugai(this)"></td>
            </tr>
            <tr>
                <td class="t3">监测站名称</td>
                <td class="t2">监测站编码</td>
                <td class="t4">建设内容</td>
                <td class="t5">监测站类型</td>
                <td class="t6"><input type="button" value="查看" class="input1" onclick="chakan(this)"></td>
                <td class="t7"><input type="button" value="删除"  class="input2"></td>
                <td class="t8"><input type="button" value="配置" class="input1" onclick="peizhi(this)"></td>
                <td class="t9"><input type="button" value="修改"  class="input1"  onclick="xiugai(this)"></td>
            </tr>
            <tr>
                <td class="t3">监测站名称</td>
                <td class="t2">监测站编码</td>
                <td class="t4">建设内容</td>
                <td class="t5">监测站类型</td>
                <td class="t6"><input type="button" value="查看" class="input1" onclick="chakan(this)"></td>
                <td class="t7"><input type="button" value="删除"  class="input2"></td>
                <td class="t8"><input type="button" value="配置" class="input1" onclick="peizhi(this)"></td>
                <td class="t9"><input type="button" value="修改"  class="input1"  onclick="xiugai(this)"></td>
            </tr>
            <tr>
                <td class="t3">监测站名称</td>
                <td class="t2">监测站编码</td>
                <td class="t4">建设内容</td>
                <td class="t5">监测站类型</td>
                <td class="t6"><input type="button" value="查看" class="input1" onclick="chakan(this)"></td>
                <td class="t7"><input type="button" value="删除"  class="input2"></td>
                <td class="t8"><input type="button" value="配置" class="input1" onclick="peizhi(this)"></td>
                <td class="t9"><input type="button" value="修改"  class="input1"  onclick="xiugai(this)"></td>
            </tr>
            <tr>
                <td class="t3">监测站名称</td>
                <td class="t2">监测站编码</td>
                <td class="t4">建设内容</td>
                <td class="t5">监测站类型</td>
                <td class="t6"><input type="button" value="查看" class="input1" onclick="chakan(this)"></td>
                <td class="t7"><input type="button" value="删除"  class="input2"></td>
                <td class="t8"><input type="button" value="配置" class="input1" onclick="peizhi(this)"></td>
                <td class="t9"><input type="button" value="修改"  class="input1"  onclick="xiugai(this)"></td>
            </tr>
            </tbody>
        </table>
        <div  id="page">
        </div>

        <div id="chakan">
            <table id="bg1">
                <tr>
                    <td class="s1">监测站名称</td>
                    <td class="s2"></td>
                </tr>
                <tr>
                    <td class="s1">监测站编码</td>
                    <td class="s2"></td>
                </tr>
                <tr>
                    <td class="s1">建设时间</td>
                    <td class="s2"></td>
                </tr>
                <tr>
                    <td class="s1">建设内容</td>
                    <td class="s2"></td>
                </tr>
                <tr>
                    <td class="s1">监测站类型</td>
                    <td class="s2"></td>
                </tr>
                <tr>
                    <td class="s1">资金来源</td>
                    <td class="s2"></td>
                </tr>
                <tr>
                    <td class="s1">监测站位置</td>
                    <td class="s2"></td>
                </tr>
                <tr>
                    <td class="s1">经纬度</td>
                    <td class="s2"></td>
                </tr>
                <tr>
                    <td class="s1">使用单位，联系人，联系方式</td>
                    <td class="s2"></td>
                </tr >
                <tr>
                    <td class="s1">施工单位，联系人，联系方式</td>
                    <td class="s2"></td>
                </tr>
                <tr>
                    <td class="s1">网络类型</td>
                    <td class="s2"></td>
                </tr>
                <tr>
                    <td class="s1">网关类型</td>
                    <td class="s2"></td>
                </tr>
                <tr>
                    <td class="s1">监测站描述</td>
                    <td class="s2"></td>
                </tr>
            </table>
            <form>
                <input type="button" value="关闭" onclick="guanbi()" />
            </form>
        </div>
        <div id="xiugai">
            <form>
                <span>监测站名称:</span>
                <input type="text" >
                <br/>
                <span>监测站编码:</span>
                <input type="text" >
                <br/>
                <span>建设时间:</span>
                <input type="text" style="margin-left: 28px;">
                <br/>
                <span>使用单位，联系人，联系方式:</span>
                <input type="text" class="lxfs">
                <br/>
                <span>施工单位，联系人，联系方式:</span>
                <input type="text" class="lxfs">
                <br/>
                <span >监测站类型:</span>
                <select style="margin-left: 1%;">
                    <option>新建重点监测站</option>
                    <option>改建重点监测站</option>
                    <option>新建普通监测站</option>
                    <option>改建普通监测站</option>
                </select>
                <br/>
                <span>资金来源:</span>
                <select>
                    <option>省资金</option>

                    <option>国家资金</option>
                    <option>其他</option>
                </select>
                <br/>
                <span>网络类型:</span>
                <select>
                    <option>无线</option>

                    <option>有线</option>
                </select>
                <br/>
                <span>网关类型:</span>
                <select>
                    <option>NZ2000</option>

                    <option>NZ1000</option>
                </select>
                <br/>
                <span>监测性质:</span>
                <input type="checkbox" name="gn" class="gn">病害监测
                <input type="checkbox" name="gn"  class="gn">害虫监测
                <input type="checkbox" name="gn"  class="gn"> 鼠情监测
                <input type="checkbox" name="gn"  class="gn">环境因子监测
                <input type="checkbox" name="gn"  class="gn">视频图像监测
                <input type="checkbox" name="gn"  class="gn">其他监测
                <br/>
                <span >监测站描述:</span>
                <textarea></textarea>
                <input class="xg" type="button" value="修改" onclick="xg()">
            </form>
        </div>
        <div id="tianjia">
            <form>
                <span>监测站名称:</span>
                <input type="text" >
                <br/>
                <span>监测站编码:</span>
                <input type="text" >
                <br/>
                <span>建设时间:</span>
                <input type="text" style="margin-left: 28px;">
                <br/>
                <span>使用单位，联系人，联系方式:</span>

                <input type="text" class="lxfs">
                <br/>
                <span>施工单位，联系人，联系方式:</span>
                <input type="text" class="lxfs">
                <br/>
                <span >监测站类型:</span>
                <select style="margin-left: 1%;">
                    <option>新建重点监测站</option>
                    <option>改建重点监测站</option>
                    <option>新建普通监测站</option>
                    <option>改建普通监测站</option>
                </select>
                <br/>
                <span>资金来源:</span>
                <select>
                    <option>省资金</option>

                    <option>国家资金</option>
                    <option>其他</option>
                </select>
                <br/>
                <span>网络类型:</span>
                <select>
                    <option>无线</option>

                    <option>有线</option>
                </select>
                <br/>
                <span>网关类型:</span>
                <select>
                    <option>NZ2000</option>

                    <option>NZ1000</option>
                </select>
                <br/>
                <span>监测性质:</span>
                <input type="checkbox" name="gn" class="gn">病害监测
                <input type="checkbox" name="gn"  class="gn">害虫监测
                <input type="checkbox" name="gn"  class="gn"> 鼠情监测
                <input type="checkbox" name="gn"  class="gn">环境因子监测
                <input type="checkbox" name="gn"  class="gn">视频图像监测
                <input type="checkbox" name="gn"  class="gn">其他监测
                <br/>
                <span >监测站描述:</span>
                <textarea></textarea>
                <input class="tj" type="button" value="添加" onclick="tj()">
            </form>
        </div>
        <div id="peizhi">
            <form>
                <span>监测站名称:</span><input type="text"/>
                <br/>
                <span>监测站编码:</span><input type="text"/>
                <br/>
                <span style="margin-right: 16px;">上传地址:</span><input type="text">
                <br/>
                <span style="margin-right: 16px;">上传频率:</span><input type="text"/>
                <br/>
                <span style="margin-right: 28px;">端口号:</span> <input type="text"/>
                <br/>
                <span style="margin-right: 16px;">存储周期:</span><input type="text"/>
                <br/>
                <input class="pz" type="button" value="配置" onclick="pz()">
            </form>
        </div>

    </div>
</div>
<div id="footer">
    <li>济南农智信息科技有限公司所有&copy; &nbsp;电话：12345677  &nbsp;<a href="#">关于我们</a> &nbsp;<a href="#">售后服务</a></li>
</div>
</body>
</html>