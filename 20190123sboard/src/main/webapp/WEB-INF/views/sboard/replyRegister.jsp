<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<script type="text/javascript" src="/resources/smarteditor/js/service/HuskyEZCreator.js"></script>

	<section class="content">
		<div class="row">
			<div class="col-md-12">
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title">REGISTER BOARD</h3>
					</div>
					<form id="registerForm" action="replyRegister" method="post">
					<div class="box-body">
						<div class="form-group">
							<label>TITLE</label>
							<input type="text" name="title" class="form-control" />
						</div>
						<div class="form-group">
							<label>CONTENT</label>
							<textarea name="content" id="ir1" rows="3" class="form-control"></textarea>
						</div>
						<div class="form-group">
							<label>WRITER</label>
							<input type="text" name="writer" class="form-control" value="${userInfo.uname}" readonly />
						</div>
					</div>
					<div class="box-footer">
						<input type="hidden" name="uno" value="${userInfo.uno}"/>
						<input type="hidden" name="origin" value="${boardVo.origin}"/>
						<input type="hidden" name="depth" value="${boardVo.depth}"/>
						<input type="hidden" name="seq" value="${boardVo.seq}"/>
						<input type="submit" class="btn btn-primary" value="SAVE"/>
					</div>
					
					</form>
				</div>
			</div>			
		</div>
	</section>
	<script>
		var oEditors = [];
		nhn.husky.EZCreator.createInIFrame(
			oEditors,
			"ir1",
			"/resources/smarteditor/SmartEditor2Skin.html",
			"createSEditor2"
		);
		
		$("#registerForm").submit(function(event){
			event.preventDefault();
			
			oEditors[0].exec("UPDATE_CONTENTS_FIELD",[]);
			$(this).get(0).submit();
		});
	
	</script>
<%@ include file="../include/footer.jsp" %>