<!DOCTYPE html>
<!-- 
Copyright@ LINE 2019
-->
<html lang="Ja">
<head>
    <meta charset="UTF-8">
  	<meta name="description" content="LINE Technical Partner ChatBot">
  	<meta name="keywords" content="LINE, ChatBot, Seminar, LIFF, FlexMessage">
  	<meta name="author" content="jounghunyoung@gmail.com">
  	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" id="line_youg_css" href="css/style.css" type="text/css" media="all">
    <script id="jQuery" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="js/seminar.js"></script>
    <title>セミナー情報</title>
</head>
<body>
<div class="content">
    <div class="error_division"></div>
    <div class="topnav">
	     <h2>セミナー</h2>
    </div>
    
    <div class="content">
		<table>	
			<tr>
				<td>
					<p>1. 日程</p>
					<ul class="title">
					  	<li>2019年3月13日 14:00〜16:00</li>
					</ul>
					
					<p>2. 場所</p>
					<ul class="title">
						<li>コンフォート新宿<br/>
							東京都新宿区新宿4丁目3番25号 TOKYU REIT 新宿ビル 7階 Room A<br/>
							<a href="#" id="show_map">場所情報はこちら</a>
						</li>
					</ul>
					
					<p>3. アジェンダ</p>
					<ul class="title">
					  	<li>Opening</li>
					  	<li>LINE Thingsご紹介
							<ul class="sub">
								<li>LINE Thingsの概要</li>
								<li>LINE Thingsの強み・メリット</li>
								<li>ユースケースご紹介・デモ</li>
							</ul>
					  	</li>
					  	<li>休憩</li>
					  	<li>CLOVAのご紹介
							<ul class="sub">
								<li>CLOVAの概要</li>
								<li>CLOVAの作り方</li>
								<li>事例・ユースケースのご紹介</li>
								<li>CLOVAとMessagingAPIの連隊について</li>
								<li>勉強会運営Botご紹介・デモ</li>
							</ul>
					  	</li>
					  	<li>QA</li>
					</ul>
					
				</td>
			</tr>
			<tr>
				<td align="center">
					<hr>
				</td>
			</tr>
			<tr>
				<td align="center">
                    <select name="category" id="category">
                    	<option value="0" selected="selected">--カテゴリーを選択してください--</option>
                        <option value="1">LINE Things</option>
                        <option value="2">CLOVA</option>
                        <option value="3">勉強会運営BOT</option>
                        <option value="4">その他</option>
                    </select>
				</td>
			</tr>
			<tr>
				<td align="center">
					<textarea rows="5" cols="35%" id="content">セミナー内容に対して質問をしてください</textarea>
				</td>
			</tr>
			<tr>
				<td align="center">
					<hr>
				</td>
			</tr>
			<tr>
				<td align="center">
					<button class="btn submit" id="submit"> 送　信 </button>
				</td>
			</tr>
		</table>
	</div>

	<div class="footer">
  		<p>Copyright@ LINE 2019</p>
	</div>
</div>
</body>
</html>