<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>一言白条</title>

    <!-- Bootstrap core CSS -->
    <link href="/static/page/css/bootstrap.min.css" rel="stylesheet">

    <!-- Animation CSS -->
    <link href="/static/page/css/animate.css" rel="stylesheet">
    <link href="/static/page/font-awesome/css/font-awesome.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/static/page/css/style.css" rel="stylesheet">
    <link rel="shortcut icon" href="/favicon.ico" />
</head>
<body id="page-top" class="landing-page no-skin-config">
<div class="navbar-wrapper">
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#"><img src="static/ace/css/logo.gif"></a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a class="page-scroll" href="#page-top">主页</a></li>
                    <li><a class="page-scroll" href="#features">关于我们</a></li>
                    <li><a class="page-scroll" href="#team">新手上路</a></li>
                    <li><a class="page-scroll" href="#testimonials">联系我们</a></li>
                    <%--<li><a class="page-scroll" href="#pricing">Pricing</a></li>--%>
                    <li><a class="page-scroll" href="#contact">我的白条</a></li>
                </ul>
            </div>
        </div>
    </nav>
</div>
<div id="inSlider" class="carousel carousel-fade" data-ride="carousel">
    <ol class="carousel-indicators">
        <li data-target="#inSlider" data-slide-to="0" class="active"></li>
        <li data-target="#inSlider" data-slide-to="1"></li>
    </ol>
    <div class="carousel-inner" role="listbox">
        <div class="item active">
            <div class="container">
                <div class="carousel-caption">
                    <h1><br>从一言开始<br>享受<br>更好的金融生活……<br></h1>
                    <p>ENJOIN YOUR LIFE</p>
                    <p>
                        <a class="btn btn-lg btn-primary" href="front/goApply" role="button"> 我要消费 </a>
                        <a class="btn btn-lg btn-primary" href="front/goApply" role="button"> 我要现金 </a>
                    </p>
                </div>
                <div class="carousel-image wow zoomIn">
                    <img src="/static/page/img/landing/laptop.png" alt="laptop"/>
                </div>
            </div>
            <!-- Set background for slide in css -->
            <div class="header-back one"></div>

        </div>
        <div class="item">
            <div class="container">
                <div class="carousel-caption blank">
                    <h1 style="margin-left:200px"><br>从一言开始<br>享受<br>更好的金融生活……<br></h1>
                    <p style="margin-left:200px">ENJOIN YOUR LIFE</p>
                    <p style="margin-left:200px">
                        <a class="btn btn-lg btn-primary" href="/static/page/#" role="button"> 我要消费 </a>
                        <a class="btn btn-lg btn-primary" href="/static/page/#" role="button"> 我要现金 </a></p>
                </div>
            </div>
            <!-- Set background for slide in css -->
            <div class="header-back two"></div>
        </div>
    </div>
    <a class="left carousel-control" href="/static/page/#inSlider" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="/static/page/#inSlider" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>


<section id="features" class="container services">
    <div class="row">
        <div class="col-sm-3">
            <h2>消费贷</h2>
            <p>服务与企业集客用户，通过旗下暖猫商城，精选品质商品，让集客用户从商品和金融体验双重快感</p>
            <p><a class="navy-link" href="#" role="button">详细 &raquo;</a></p>
        </div>
        <div class="col-sm-3">
            <h2>
                商品倾心
                <br>
                额度满足
                <br>
                账期灵活
            </h2>
        </div>
        <div class="col-sm-3">
            <h2>现金贷</h2>
            <p>服务与企业集客用户，通过旗下暖猫商城，精选品质商品，让集客用户从商品和金融体验双重快感</p>
            <p><a class="navy-link" href="#" role="button">详细 &raquo;</a></p>
        </div>
        <div class="col-sm-3">
            <h2>
                无需抵押
                <br>
                手续简便
                <br>
                极速审核
            </h2>

        </div>
    </div>
</section>



<section class="comments gray-section" style="margin-top: 0">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <div class="navy-line"></div>
                <h1>关于我们</h1>
            </div>
        </div>
        <div class="row features-block">
            <div class="col-lg-4">
                <div class="bubble">
                    "Uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like)."
                </div>
                <div class="comments-avatar">
                    <a href="/static/page/" class="pull-left">
                        <img alt="image" src="/static/page/img/landing/avatar8.jpg">
                    </a>
                    <div class="media-body">
                        <div class="commens-name">
                            Andrew Williams
                        </div>
                        <small class="text-muted">Company X from California</small>
                    </div>
                </div>
            </div>

            <div class="col-lg-4">
                <div class="bubble">
                    "Uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like)."
                </div>
                <div class="comments-avatar">
                    <a href="/static/page/" class="pull-left">
                        <img alt="image" src="/static/page/img/landing/avatar7.jpg">
                    </a>
                    <div class="media-body">
                        <div class="commens-name">
                            Andrew Williams
                        </div>
                        <small class="text-muted">Company X from California</small>
                    </div>
                </div>
            </div>

            <div class="col-lg-4">
                <div class="bubble">
                    "Uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like)."
                </div>
                <div class="comments-avatar">
                    <a href="/static/page/" class="pull-left">
                        <img alt="image" src="/static/page/img/landing/avatar5.jpg">
                    </a>
                    <div class="media-body">
                        <div class="commens-name">
                            Andrew Williams
                        </div>
                        <small class="text-muted">Company X from California</small>
                    </div>
                </div>
            </div>



        </div>
    </div>

</section>

<section class="features">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <div class="navy-line"></div>
                <h1>服务特色</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-5 col-lg-offset-1 features-text">
                <small>FIRST</small>
                <h2>商品倾心</h2>
                <i class="fa fa-users big-icon pull-right"></i>
                <p>INSPINIA Admin Theme is a premium admin dashboard template with flat design concept. It is fully responsive admin dashboard template built with Bootstrap 3+ Framework, HTML5 and CSS3, Media query. It has a huge collection of reusable UI components and integrated with.</p>
            </div>
            <div class="col-lg-5 features-text">
                <small>SECOND</small>
                <h2>额度满足</h2>
                <i class="fa fa-yen big-icon pull-right"></i>
                <p>INSPINIA Admin Theme is a premium admin dashboard template with flat design concept. It is fully responsive admin dashboard template built with Bootstrap 3+ Framework, HTML5 and CSS3, Media query. It has a huge collection of reusable UI components and integrated with.</p>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-5 col-lg-offset-1 features-text">
                <small>THIRD</small>
                <h2>账期灵活 </h2>
                <i class="fa fa-calendar-o big-icon pull-right"></i>
                <p>INSPINIA Admin Theme is a premium admin dashboard template with flat design concept. It is fully responsive admin dashboard template built with Bootstrap 3+ Framework, HTML5 and CSS3, Media query. It has a huge collection of reusable UI components and integrated with.</p>
            </div>
            <div class="col-lg-5 features-text">
                <small>FORTH</small>
                <h2>无需抵押</h2>
                <i class="fa fa-credit-card big-icon pull-right"></i>
                <p>INSPINIA Admin Theme is a premium admin dashboard template with flat design concept. It is fully responsive admin dashboard template built with Bootstrap 3+ Framework, HTML5 and CSS3, Media query. It has a huge collection of reusable UI components and integrated with.</p>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-5 col-lg-offset-1 features-text">
                <small>FIFTH</small>
                <h2>手续简便</h2>
                <i class="fa fa-clock-o big-icon pull-right"></i>
                <p>INSPINIA Admin Theme is a premium admin dashboard template with flat design concept. It is fully responsive admin dashboard template built with Bootstrap 3+ Framework, HTML5 and CSS3, Media query. It has a huge collection of reusable UI components and integrated with.</p>
            </div>
            <div class="col-lg-5 features-text">
                <small>SIXTH</small>
                <h2>极速审核</h2>
                <i class="fa fa-bolt big-icon pull-right"></i>
                <p>INSPINIA Admin Theme is a premium admin dashboard template with flat design concept. It is fully responsive admin dashboard template built with Bootstrap 3+ Framework, HTML5 and CSS3, Media query. It has a huge collection of reusable UI components and integrated with.</p>
            </div>
        </div>
    </div>

</section>

<section id="contact" class="gray-section contact">
    <div class="container">
        <div class="row m-b-lg">
            <div class="col-lg-12 text-center">
                <div class="navy-line"></div>
                <h1>联系我们</h1>
            </div>
        </div>
        <div class="row m-b-lg">
            <div class="col-lg-3 col-lg-offset-3">
                <address>
                    <strong><span class="navy">热线电话</span></strong><br/>
                    010-82788860<br/>
                   周一至周五：
                    <abbr title="Phone">10:00 - 18:00</abbr>
                </address>
            </div>
            <div class="col-lg-4">
                <p class="text-color" style="text-align:center">
                        <img src="static/page/img/landing/erweima.png">
                </p>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12 text-center">
                <a href="mailto:test@email.com" class="btn btn-primary">Send us mail</a>

            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2 text-center m-t-lg m-b-lg">
                <p><strong>中联恒天控股有限公司 liangziCredit Co..Ltd Copyright©2017版权所有 京ICP备15035725号</strong>.</p>
            </div>
        </div>
    </div>
</section>

<!-- Mainly scripts -->
<script src="/static/page/js/jquery-3.1.1.min.js"></script>
<script src="/static/page/js/bootstrap.min.js"></script>
<script src="/static/page/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="/static/page/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<!-- Custom and plugin javascript -->
<script src="/static/page/js/inspinia.js"></script>
<script src="/static/page/js/plugins/pace/pace.min.js"></script>
<script src="/static/page/js/plugins/wow/wow.min.js"></script>


<script>

    $(document).ready(function () {

        $('body').scrollspy({
            target: '.navbar-fixed-top',
            offset: 80
        });

        // Page scrolling feature
        $('a.page-scroll').bind('click', function(event) {
            var link = $(this);
            $('html, body').stop().animate({
                scrollTop: $(link.attr('href')).offset().top - 50
            }, 500);
            event.preventDefault();
            $("#navbar").collapse('hide');
        });
    });

    var cbpAnimatedHeader = (function() {
        var docElem = document.documentElement,
                header = document.querySelector( '.navbar-default' ),
                didScroll = false,
                changeHeaderOn = 200;
        function init() {
            window.addEventListener( 'scroll', function( event ) {
                if( !didScroll ) {
                    didScroll = true;
                    setTimeout( scrollPage, 250 );
                }
            }, false );
        }
        function scrollPage() {
            var sy = scrollY();
            if ( sy >= changeHeaderOn ) {
                $(header).addClass('navbar-scroll')
            }
            else {
                $(header).removeClass('navbar-scroll')
            }
            didScroll = false;
        }
        function scrollY() {
            return window.pageYOffset || docElem.scrollTop;
        }
        init();

    })();

    // Activate WOW.js plugin for animation on scrol
    new WOW().init();

</script>

</body>
</html>
